import React, { useState } from 'react';
import { useAuth } from './context/AuthContext';
import Auth from './pages/Auth';
import Feed from './pages/Feed';
import Profile from './pages/Profile';
import { LogOut, Layout } from 'lucide-react';

function App() {
  const { user, loading, logout } = useAuth();
  const [currentPage, setCurrentPage] = useState('feed'); // 'feed' | 'profile'

  if (loading) return <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>Loading...</div>;

  if (!user) return <Auth />;

  return (
    <div className="app-container">
      <nav className="glass" style={{ 
        position: 'fixed', top: '20px', left: '50%', transform: 'translateX(-50%)',
        width: '90%', maxWidth: '1200px', padding: '15px 30px', 
        display: 'flex', justifyContent: 'space-between', alignItems: 'center',
        zIndex: 1000, borderRadius: '20px'
      }}>
        <div style={{ display: 'flex', alignItems: 'center', gap: '10px' }}>
          <div className="btn-primary" style={{ padding: '8px', borderRadius: '10px' }}><Layout size={20} /></div>
          <h1 style={{ fontSize: '20px', fontWeight: '800' }}>AskMe</h1>
        </div>

        <div style={{ display: 'flex', alignItems: 'center', gap: '24px' }}>
          <span 
            onClick={() => setCurrentPage('feed')}
            style={{ cursor: 'pointer', fontWeight: '500', color: currentPage === 'feed' ? 'var(--text-main)' : 'var(--text-dim)' }}
          >Feed</span>
          <span 
            onClick={() => setCurrentPage('profile')}
            style={{ cursor: 'pointer', fontWeight: '500', color: currentPage === 'profile' ? 'var(--text-main)' : 'var(--text-dim)' }}
          >My Profile</span>
          <button 
            onClick={logout}
            style={{ 
              background: 'rgba(239, 68, 68, 0.1)', border: '1px solid rgba(239, 68, 68, 0.2)',
              color: '#ef4444', padding: '8px 16px', borderRadius: '10px',
              display: 'flex', alignItems: 'center', gap: '8px', cursor: 'pointer'
            }}
          >
            <LogOut size={16} /> Logout
          </button>
        </div>
      </nav>

      <main>
        {currentPage === 'feed' ? <Feed /> : <Profile />}
      </main>
    </div>
  );
}

export default App;
