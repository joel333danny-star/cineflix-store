import { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import { Heart, PlayCircle, ShoppingCart } from 'lucide-react'
import { api, USER_ID, money } from '../services/api'
import { useCart } from '../context/CartContext.jsx'
import RatingStars from '../components/RatingStars.jsx'
import TrailerModal from '../components/TrailerModal.jsx'

export default function DetallePelicula() {
  const { id } = useParams()
  const [movie, setMovie] = useState(null)
  const [reviews, setReviews] = useState([])
  const [showTrailer, setShowTrailer] = useState(false)
  const [form, setForm] = useState({ calificacion: 5, comentario: '' })
  const { addToCart } = useCart()

  async function load() {
    const [{ data: pelicula }, { data: resenas }] = await Promise.all([
      api.get(`/peliculas/${id}`),
      api.get(`/resenas/pelicula/${id}`)
    ])
    setMovie(pelicula)
    setReviews(resenas)
  }

  useEffect(() => { load().catch(console.error) }, [id])

  async function addReview(e) {
    e.preventDefault()
    await api.post('/resenas', { usuarioId: USER_ID, peliculaId: Number(id), ...form })
    setForm({ calificacion: 5, comentario: '' })
    load()
  }

  async function fav() {
    await api.post(`/favoritos/${USER_ID}/${id}`)
    alert('Agregada a favoritos')
  }

  if (!movie) return <main className="page">Cargando...</main>

  return (
    <main>
      <section className="detail-hero" style={{ backgroundImage: `linear-gradient(90deg, #08080c 20%, rgba(8,8,12,.75), #08080c), url(${movie.posterUrl})` }}>
        <img className="detail-poster" src={movie.posterUrl} alt={movie.titulo} />
        <div className="detail-info">
          <span className="badge big">{movie.tipoProducto}</span>
          <h1>{movie.titulo}</h1>
          <div className="detail-meta"><RatingStars rating={movie.rating} /> · {movie.anio} · {movie.genero?.nombre} · {movie.director}</div>
          <p>{movie.descripcion}</p>
          <p><strong>Actor principal:</strong> {movie.actorPrincipal}</p>
          <div className="detail-actions">
            <button className="main-btn" onClick={() => setShowTrailer(true)}><PlayCircle /> Ver tráiler</button>
            <button className="outline-btn" onClick={() => addToCart(movie.id, 'RENTA')}><ShoppingCart /> Rentar {money(movie.precioRenta)}</button>
            <button className="outline-btn" onClick={() => addToCart(movie.id, 'COMPRA')}>Comprar {money(movie.precioCompra)}</button>
            <button className="heart-btn" onClick={fav}><Heart /></button>
          </div>
        </div>
      </section>

      <section className="page two-columns">
        <div>
          <h2>Reseñas de usuarios</h2>
          <div className="reviews">
            {reviews.length === 0 && <p className="muted">Sé el primero en opinar.</p>}
            {reviews.map(r => (
              <div className="review" key={r.id}>
                <RatingStars rating={r.calificacion * 2} />
                <p>{r.comentario}</p>
                <small>{new Date(r.fecha).toLocaleString('es-MX')}</small>
              </div>
            ))}
          </div>
        </div>
        <form className="review-form" onSubmit={addReview}>
          <h2>Dejar reseña</h2>
          <label>Calificación</label>
          <select value={form.calificacion} onChange={e => setForm({ ...form, calificacion: Number(e.target.value) })}>
            {[1, 2, 3, 4, 5].map(n => <option key={n} value={n}>{n} estrellas</option>)}
          </select>
          <label>Opinión</label>
          <textarea value={form.comentario} onChange={e => setForm({ ...form, comentario: e.target.value })} required />
          <button className="main-btn">Publicar</button>
        </form>
      </section>

      {showTrailer && <TrailerModal url={movie.trailerUrl} onClose={() => setShowTrailer(false)} />}
    </main>
  )
}
