import { useEffect, useMemo, useState } from 'react'
import { Link } from 'react-router-dom'
import { api } from '../services/api'
import MovieCarousel from '../components/MovieCarousel.jsx'
import { PlayCircle, Sparkles } from 'lucide-react'

export default function Home() {
  const [movies, setMovies] = useState([])

  useEffect(() => {
    api.get('/peliculas').then(({ data }) => setMovies(data)).catch(console.error)
  }, [])

  const hero = movies[1] || movies[0]
  const byGenre = useMemo(() => ({
    alquiler: movies.slice(0, 7),
    compra: movies.slice(7, 14),
    animacion: movies.filter(m => m.genero?.nombre === 'Animación'),
    accion: movies.filter(m => m.genero?.nombre === 'Acción'),
    documental: movies.filter(m => m.tipoProducto === 'DOCUMENTAL')
  }), [movies])

  return (
    <main>
      <section className="hero" style={{ backgroundImage: hero ? `linear-gradient(90deg, rgba(7,7,10,.96), rgba(7,7,10,.55), rgba(7,7,10,.96)), url(${hero.posterUrl})` : undefined }}>
        <div className="hero-content">
          <span className="eyebrow"><Sparkles size={16} /> Estreno premium</span>
          <h1>{hero?.titulo || 'CineFlix Store'}</h1>
          <p>{hero?.descripcion || 'Compra y renta películas digitales con factura automática.'}</p>
          <div className="hero-actions">
            <Link to={hero ? `/pelicula/${hero.id}` : '/catalogo'} className="main-btn"><PlayCircle /> Ver ahora</Link>
            <Link to="/catalogo" className="outline-btn">Explorar catálogo</Link>
          </div>
        </div>
      </section>

      <MovieCarousel title="Películas más vistas en Alquiler" movies={byGenre.alquiler} />
      <MovieCarousel title="Películas populares en Compra" movies={byGenre.compra} />
      <MovieCarousel title="Aventuras de Animación en Alquiler" movies={byGenre.animacion} />
      <MovieCarousel title="Acción y Premium" movies={byGenre.accion} />
      <MovieCarousel title="Documentales" movies={byGenre.documental} />
    </main>
  )
}
