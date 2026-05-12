import React, { useState, useEffect } from 'react';
import { motion, AnimatePresence } from 'framer-motion';
import { Search, Send, User as UserIcon, MessageSquare, CheckCircle } from 'lucide-react';
import { useAuth } from '../context/AuthContext';

const QuestionList = ({ questions, type, selectedQ, setSelectedQ, answerText, setAnswerText, handleAnswer }) => (
  <div style={{ display: 'flex', flexDirection: 'column', gap: '20px' }}>
    {questions?.length === 0 ? (
      <div className="glass" style={{ padding: '40px', textAlign: 'center', color: 'var(--text-dim)' }}>No questions here yet.</div>
    ) : (
      questions?.map((q, i) => (
        <motion.div 
          layout
          key={q.id || i} 
          className="glass" 
          style={{ padding: '24px', borderLeft: `4px solid ${q.answer ? '#10b981' : 'var(--accent)'}` }}
        >
          <div style={{ display: 'flex', justifyContent: 'space-between', marginBottom: '12px' }}>
             <span style={{ fontSize: '12px', color: 'var(--text-dim)', fontWeight: '600' }}>
                ID: #{q.id} • {type === 'received' ? `From: ${q.asker?.userName || 'Anonymous'}` : `To: ${q.askedTo?.userName}`}
             </span>
             {q.answer && <CheckCircle size={16} color="#10b981" />}
          </div>
          
          <p style={{ fontSize: '18px', color: 'var(--text-main)', marginBottom: '16px', lineHeight: '1.5' }}>{q.question}</p>
          
          {q.answer ? (
            <div style={{ background: 'rgba(16, 185, 129, 0.05)', padding: '16px', borderRadius: '12px', border: '1px solid rgba(16, 185, 129, 0.1)' }}>
              <p style={{ fontSize: '14px', color: '#10b981', marginBottom: '4px', fontWeight: '600' }}>Answered:</p>
              <p style={{ fontSize: '16px', color: 'var(--text-main)' }}>{q.answer}</p>
            </div>
          ) : type === 'received' ? (
            selectedQ === q.id ? (
              <div style={{ marginTop: '16px' }}>
                <textarea 
                  className="input-glass" 
                  style={{ height: '80px', marginBottom: '12px', resize: 'none' }}
                  placeholder="Type your answer..."
                  value={answerText}
                  onChange={e => setAnswerText(e.target.value)}
                  autoFocus
                />
                <div style={{ display: 'flex', gap: '10px' }}>
                  <button className="btn-primary" style={{ padding: '8px 20px' }} onClick={() => handleAnswer(q.id)}>Submit Answer</button>
                  <button className="glass" style={{ padding: '8px 20px' }} onClick={() => setSelectedQ(null)}>Cancel</button>
                </div>
              </div>
            ) : (
              <button className="btn-primary" style={{ padding: '8px 20px', fontSize: '14px' }} onClick={() => setSelectedQ(q.id)}>
                Answer this question
              </button>
            )
          ) : (
            <p style={{ fontSize: '14px', color: 'var(--text-dim)', fontStyle: 'italic' }}>Waiting for answer...</p>
          )}
        </motion.div>
      ))
    )}
  </div>
);

const Profile = () => {
  const { user, login } = useAuth();
  const [users, setUsers] = useState([]);
  const [targetUser, setTargetUser] = useState(null);
  const [questionText, setQuestionText] = useState('');
  const [activeTab, setActiveTab] = useState('received'); // 'received' | 'sent' | 'ask'
  const [status, setStatus] = useState('');
  const [answerText, setAnswerText] = useState('');
  const [selectedQ, setSelectedQ] = useState(null);

  useEffect(() => {
    fetchUsers();
    refreshUserData();
  }, []);

  const refreshUserData = async () => {
    try {
      const res = await fetch(`http://localhost:8080/api/auth/login`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ userName: user.userName, passWord: user.passWord })
      });
      if (res.ok) {
        const data = await res.json();
        login(data);
      }
    } catch (err) { console.error(err); }
  };

  const fetchUsers = async () => {
    const res = await fetch('http://localhost:8080/api/users');
    const data = await res.json();
    setUsers(data.filter(u => u.userName !== user.userName));
  };

  const handleAsk = async (e) => {
    e.preventDefault();
    if (!targetUser || !questionText) return;
    try {
      const res = await fetch(`http://localhost:8080/api/questions/ask?asker=${user.userName}&askedTo=${targetUser.userName}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(questionText)
      });
      if (res.ok) {
        setStatus('Question sent successfully!');
        setQuestionText('');
        setTargetUser(null);
        setTimeout(() => setStatus(''), 3000);
      }
    } catch (err) { setStatus('Failed to send question.'); }
  };

  const handleAnswer = async (qId) => {
    if (!answerText) return;
    try {
      const res = await fetch(`http://localhost:8080/api/questions/answer?questionId=${qId}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(answerText)
      });
      if (res.ok) {
        setAnswerText('');
        setSelectedQ(null);
        refreshUserData();
      }
    } catch (err) { console.error(err); }
  };

  return (
    <div style={{ maxWidth: '900px', margin: '100px auto', padding: '20px' }}>
      <div className="glass" style={{ padding: '40px', marginBottom: '40px', display: 'flex', alignItems: 'center', gap: '30px' }}>
        <div style={{ background: 'linear-gradient(135deg, var(--primary), var(--secondary))', padding: '25px', borderRadius: '24px', boxShadow: '0 10px 20px rgba(99, 102, 241, 0.2)' }}>
          <UserIcon size={48} color="white" />
        </div>
        <div>
          <h2 style={{ fontSize: '32px', fontWeight: '800', marginBottom: '4px' }}>{user.userName}</h2>
          <p style={{ color: 'var(--text-dim)', fontSize: '16px' }}>Member ID: <span style={{ color: 'var(--accent)' }}>#{user.id}</span></p>
        </div>
      </div>

      <div style={{ display: 'flex', gap: '15px', marginBottom: '32px' }}>
        {[
          { id: 'received', label: 'Received', icon: <MessageSquare size={18}/> },
          { id: 'sent', label: 'Sent', icon: <Send size={18}/> },
          { id: 'ask', label: 'Ask Someone', icon: <Search size={18}/> }
        ].map(tab => (
          <button 
            key={tab.id}
            className={activeTab === tab.id ? 'btn-primary' : 'glass'} 
            style={{ padding: '12px 24px', borderRadius: '16px', cursor: 'pointer', display: 'flex', alignItems: 'center', gap: '10px', transition: '0.3s' }}
            onClick={() => setActiveTab(tab.id)}
          >
            {tab.icon} {tab.label}
          </button>
        ))}
      </div>

      <AnimatePresence mode="wait">
        <motion.div
          key={activeTab}
          initial={{ opacity: 0, x: 10 }}
          animate={{ opacity: 1, x: 0 }}
          exit={{ opacity: 0, x: -10 }}
          transition={{ duration: 0.2 }}
        >
          {activeTab === 'received' && (
            <QuestionList 
              questions={user.fromOthers} 
              type="received" 
              selectedQ={selectedQ}
              setSelectedQ={setSelectedQ}
              answerText={answerText}
              setAnswerText={setAnswerText}
              handleAnswer={handleAnswer}
            />
          )}
          {activeTab === 'sent' && (
            <QuestionList 
              questions={user.toOthers} 
              type="sent" 
              selectedQ={selectedQ}
              setSelectedQ={setSelectedQ}
              answerText={answerText}
              setAnswerText={setAnswerText}
              handleAnswer={handleAnswer}
            />
          )}
          
          {activeTab === 'ask' && (
            <div className="glass" style={{ padding: '40px' }}>
              <h3 style={{ marginBottom: '24px', fontSize: '24px' }}>Find someone to ask</h3>
              {!targetUser ? (
                <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fill, minmax(250px, 1fr))', gap: '16px' }}>
                  {users.length === 0 ? (
                    <p style={{ color: 'var(--text-dim)', gridColumn: '1/-1', textAlign: 'center' }}>No other users found.</p>
                  ) : (
                    users.map(u => (
                      <div 
                        key={u.id} 
                        className="glass" 
                        style={{ padding: '20px', cursor: 'pointer', display: 'flex', justifyContent: 'space-between', alignItems: 'center', transition: '0.2s' }}
                        onClick={() => setTargetUser(u)}
                        onMouseEnter={e => e.currentTarget.style.borderColor = 'var(--accent)'}
                        onMouseLeave={e => e.currentTarget.style.borderColor = 'var(--glass-border)'}
                      >
                        <div style={{ display: 'flex', alignItems: 'center', gap: '12px' }}>
                          <div style={{ background: 'rgba(255,255,255,0.05)', padding: '8px', borderRadius: '10px' }}><UserIcon size={20}/></div>
                          <span style={{ fontWeight: '600' }}>{u.userName}</span>
                        </div>
                        <span style={{ color: 'var(--text-dim)', fontSize: '12px' }}>#{u.id}</span>
                      </div>
                    ))
                  )}
                </div>
              ) : (
                <motion.form 
                  initial={{ opacity: 0, scale: 0.95 }}
                  animate={{ opacity: 1, scale: 1 }}
                  onSubmit={handleAsk}
                >
                  <p style={{ marginBottom: '16px', fontSize: '18px' }}>Asking <span style={{ color: 'var(--accent)', fontWeight: '700' }}>{targetUser.userName}</span>:</p>
                  <textarea 
                    className="input-glass" 
                    style={{ height: '120px', marginBottom: '24px', resize: 'none', fontSize: '16px' }}
                    placeholder="Type your question here..."
                    value={questionText}
                    onChange={e => setQuestionText(e.target.value)}
                    required
                  />
                  <div style={{ display: 'flex', gap: '15px' }}>
                    <button className="btn-primary" style={{ flex: 2, padding: '14px' }} type="submit">Send Question</button>
                    <button className="glass" style={{ flex: 1 }} type="button" onClick={() => setTargetUser(null)}>Cancel</button>
                  </div>
                </motion.form>
              )}
              {status && <motion.p initial={{ opacity: 0 }} animate={{ opacity: 1 }} style={{ marginTop: '24px', color: 'var(--accent)', textAlign: 'center', fontWeight: '600' }}>{status}</motion.p>}
            </div>
          )}
        </motion.div>
      </AnimatePresence>
    </div>
  );
};

export default Profile;
