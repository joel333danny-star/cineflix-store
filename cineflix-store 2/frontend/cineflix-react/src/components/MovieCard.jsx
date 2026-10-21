import { Link } from 'react-router-dom'
import { Heart, Play, ShoppingCart } from 'lucide-react'
import RatingStars from './RatingStars.jsx'
import { money } from '../services/api'
import { useCart } from '../context/CartContext.jsx'

export default function MovieCard({ pelicula }) {
  const { addToCart } = useCart()

  return (
    <article className="movie-card">
      <Link to={`/pelicula/${pelicula.id}`} className="poster-wrap">
        {pelicula.premium && <span className="badge premium">PREMIUM</span>}
        <span className="badge uhd">UHD</span>
        <img src={pelicula.posterUrl} alt={pelicula.titulo} />
        <div className="poster-overlay"><Play /> Ver detalle</div>
      </Link>
      <div className="movie-info">
        <h3>{pelicula.titulo}</h3>
        <div className="meta-row"><RatingStars rating={pelicula.rating} /><span>{pelicula.vistas?.toLocaleString('es-MX')} vistas</span></div>
        <div className="price-row">
          <span>Renta {money(pelicula.precioRenta)}</span>
          <span>Compra {money(pelicula.precioCompra)}</span>
        </div>
        <div className="card-actions">
          <button onClick={() => addToCart(pelicula.id, 'RENTA')}><ShoppingCart size={16} /> Rentar</button>
          <button className="secondary" onClick={() => addToCart(pelicula.id, 'COMPRA')}>Comprar</button>
          <button className="icon-btn"><Heart size={17} /></button>
        </div>
      </div>
    </article>
  )
}
