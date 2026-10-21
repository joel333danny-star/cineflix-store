import { Link, NavLink, useNavigate } from 'react-router-dom'
import { Search, Film, UserRound } from 'lucide-react'
import { useState } from 'react'

export default function Navbar() {
  const [q, setQ] = useState('')
  const navigate = useNavigate()

  function submit(e) {
    e.preventDefault()
    navigate(`/catalogo?q=${encodeURIComponent(q)}`)
  }

  return (
    <header className="navbar">
      <Link to="/" className="brand"><Film size={25} /> CineFlix <span>Store</span></Link>
      <nav>
        <NavLink to="/">Inicio</NavLink>
        <NavLink to="/catalogo">Catálogo</NavLink>
        <NavLink to="/biblioteca">Biblioteca</NavLink>
        <NavLink to="/admin">Admin</NavLink>
      </nav>
      <form className="search" onSubmit={submit}>
        <Search size={18} />
        <input value={q} onChange={e => setQ(e.target.value)} placeholder="Buscar por título, género, año, director..." />
      </form>
      <div className="login-pill"><UserRound size={17} /> Demo</div>
    </header>
  )
}
