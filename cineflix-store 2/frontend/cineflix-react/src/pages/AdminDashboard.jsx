import { useEffect, useState } from 'react'
import { api, money } from '../services/api'

export default function AdminDashboard() {
  const [stats, setStats] = useState(null)

  useEffect(() => {
    api.get('/admin/estadisticas').then(({ data }) => setStats(data)).catch(console.error)
  }, [])

  if (!stats) return <main className="page">Cargando estadísticas...</main>

  return (
    <main className="page">
      <div className="page-title">
        <h1>Panel administrador</h1>
        <p>Estadísticas básicas de ventas y visualizaciones.</p>
      </div>

      <div className="stats-grid">
        <div><span>Usuarios</span><strong>{stats.usuarios}</strong></div>
        <div><span>Películas</span><strong>{stats.peliculas}</strong></div>
        <div><span>Pedidos</span><strong>{stats.pedidos}</strong></div>
        <div><span>Ventas</span><strong>{money(stats.ventasTotales)}</strong></div>
      </div>

      <section className="admin-table">
        <h2>Películas más vistas</h2>
        <table>
          <thead><tr><th>Título</th><th>Rating</th><th>Vistas</th><th>Género</th></tr></thead>
          <tbody>
            {stats.masVistas?.map(p => <tr key={p.id}><td>{p.titulo}</td><td>{p.rating}</td><td>{p.vistas}</td><td>{p.genero?.nombre}</td></tr>)}
          </tbody>
        </table>
      </section>
    </main>
  )
}
