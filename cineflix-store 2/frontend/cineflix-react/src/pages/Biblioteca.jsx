import { useEffect, useState } from 'react'
import { PlayCircle, Download } from 'lucide-react'
import { api, USER_ID } from '../services/api'

export default function Biblioteca() {
  const [items, setItems] = useState([])

  useEffect(() => {
    api.get(`/biblioteca/${USER_ID}`).then(({ data }) => setItems(data)).catch(console.error)
  }, [])

  return (
    <main className="page">
      <div className="page-title">
        <h1>Mi biblioteca</h1>
        <p>Aquí aparecen las películas compradas o rentadas después del pago.</p>
      </div>
      <div className="library-grid">
        {items.length === 0 && <p className="muted">Todavía no tienes contenido en tu biblioteca.</p>}
        {items.map(item => (
          <article className="library-card" key={item.id}>
            <img src={item.pelicula.posterUrl} alt={item.pelicula.titulo} />
            <div>
              <h3>{item.pelicula.titulo}</h3>
              <p>{item.tipoAdquisicion}</p>
              {item.fechaExpiracion && <small>Disponible hasta {new Date(item.fechaExpiracion).toLocaleDateString('es-MX')}</small>}
              <div className="card-actions">
                <button><PlayCircle size={16} /> Ver en línea</button>
                <button className="secondary"><Download size={16} /> Descarga temporal</button>
              </div>
            </div>
          </article>
        ))}
      </div>
    </main>
  )
}
