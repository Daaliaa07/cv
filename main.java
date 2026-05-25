// Particles
function createParticles() {
  const container = document.getElementById('particles');
  if (!container) return;
  for (let i = 0; i < 40; i++) {
    const p = document.createElement('div');
    p.className = 'particle';
    p.style.cssText = `
      left: ${Math.random() * 100}%;
      width: ${Math.random() * 3 + 1}px;
      height: ${Math.random() * 3 + 1}px;
      animation-duration: ${Math.random() * 15 + 10}s;
      animation-delay: ${Math.random() * 10}s;
      opacity: ${Math.random() * 0.5 + 0.2};
    `;
    container.appendChild(p);
  }
}

// Skill bars animation
function animateSkills() {
  const fills = document.querySelectorAll('.skill-fill');
  const observer = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
      if (entry.isIntersecting) {
        const fill = entry.target;
        fill.style.width = fill.dataset.width;
        observer.unobserve(fill);
      }
    });
  }, { threshold: 0.3 });
  fills.forEach(f => observer.observe(f));
}

// Scroll reveal
function scrollReveal() {
  const observer = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
      if (entry.isIntersecting) {
        entry.target.style.opacity = '1';
        entry.target.style.transform = 'translateY(0)';
      }
    });
  }, { threshold: 0.1 });
  document.querySelectorAll('.cv-card, .member-card, .supervisor-card').forEach(el => {
    el.style.opacity = '0';
    el.style.transform = 'translateY(20px)';
    el.style.transition = 'opacity 0.6s ease, transform 0.6s ease';
    observer.observe(el);
  });
}

// Cursor glow effect on cards
function cardGlow() {
  document.querySelectorAll('.member-card, .supervisor-card').forEach(card => {
    card.addEventListener('mousemove', (e) => {
      const rect = card.getBoundingClientRect();
      const x = e.clientX - rect.left;
      const y = e.clientY - rect.top;
      const glow = card.querySelector('.card-glow, .sup-glow');
      if (glow) {
        glow.style.left = x - 75 + 'px';
        glow.style.top = y - 75 + 'px';
      }
    });
  });
}

document.addEventListener('DOMContentLoaded', () => {
  createParticles();
  animateSkills();
  setTimeout(scrollReveal, 100);
  cardGlow();
});