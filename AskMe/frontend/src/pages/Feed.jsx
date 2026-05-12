import React, { useEffect, useState } from 'react';
import { motion } from 'framer-motion';
import { MessageCircle, Send, Trash2, User as UserIcon } from 'lucide-react';
import { useAuth } from '../context/AuthContext';

const Feed = () => {
  const [feed, setFeed] = useState([]);
  const { user } = useAuth();
  const [loading, setLoading] = useState(true);

  const fetchFeed = async () => {
    try {
      const res = await fetch('http://localhost:8080/api/feed');
      const data = await res.json();
      setFeed(data || []);
    } catch (err) {
      console.error('Feed failed', err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchFeed();
    const interval = setInterval(fetchFeed, 10000);
    return () => clearInterval(interval);
  }, []);

  return (
    <div style={{ maxWidth: '800px', margin: '100px auto', padding: '20px' }}>
      <header style={{ marginBottom: '40px', display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
        <h2 style={{ fontSize: '28px', fontWeight: '700' }}>Global Feed</h2>
        <div style={{ display: 'flex', alignItems: 'center', gap: '12px' }}>
          <div className="glass" style={{ padding: '8px 16px', fontSize: '14px', borderRadius: '12px', display: 'flex', alignItems: 'center', gap: '8px' }}>
             <UserIcon size={16} /> {user.userName}
          </div>
        </div>
      </header>

      <div style={{ display: 'flex', flexDirection: 'column', gap: '20px' }}>
        {feed.length === 0 ? (
          <div className="glass" style={{ padding: '40px', textAlign: 'center', color: 'var(--text-dim)' }}>
            No activity yet. Be the first to ask!
          </div>
        ) : (
          feed.map((item, idx) => (
            <motion.div 
              key={idx}
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              transition={{ delay: idx * 0.1 }}
              className="glass"
              style={{ padding: '24px' }}
            >
              <div style={{ display: 'flex', justifyContent: 'space-between', marginBottom: '16px' }}>
                <span style={{ fontSize: '12px', color: 'var(--accent)', fontWeight: '600', textTransform: 'uppercase', letterSpacing: '1px' }}>
                   Question ID: {item.id}
                </span>
                <span style={{ fontSize: '12px', color: 'var(--text-dim)' }}>
                  From: {item.asker?.userName || 'Anonymous'} → To: {item.askedTo?.userName}
                </span>
              </div>
              
              <p style={{ fontSize: '18px', fontWeight: '500', marginBottom: '16px', lineHeight: '1.5' }}>
                {item.question}
              </p>

              {item.answer ? (
                <div style={{ borderLeft: '3px solid var(--primary)', paddingLeft: '20px', marginTop: '16px' }}>
                  <p style={{ color: 'var(--text-dim)', fontSize: '14px', marginBottom: '4px' }}>Answer:</p>
                  <p style={{ fontSize: '16px' }}>{item.answer}</p>
                </div>
              ) : (
                <div style={{ marginTop: '16px', fontStyle: 'italic', color: 'var(--text-dim)', fontSize: '14px' }}>
                  Awaiting answer...
                </div>
              )}
            </motion.div>
          ))
        )}
      </div>
    </div>
  );
};

export default Feed;
