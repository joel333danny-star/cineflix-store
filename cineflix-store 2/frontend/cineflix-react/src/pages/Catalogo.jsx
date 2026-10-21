import { useEffect, useState } from 'react'
import { useSearchParams } from 'react-router-dom'
import { api } from '../services/api'
import MovieCard from '../components/MovieCard.jsx'

const generos = ['Todos', 'Acción', 'Comedia', 'Terror', 'Ciencia ficción', 'Drama', 'Romance', 'Animación', 'Suspenso', 'Documental']

export default function Catalogo() {
  const [searchParams] = useSearchParams()
  const [movies, setMovies] = useState([])
  const [genero, setGenero] = useState('Todos')
  const [tipo, setTipo] = useState('')
  const q = searchParams.get('q') || ''

  useEffect(() => {
    if (q) {
      api.get(`/peliculas/buscar?q=${encodeURIComponent(q)}`).then(({ data }) => setMovies(data))
    } else {
      const params = new URLSearchParams()
      if (genero !== 'Todos') params.set('genero', genero)
      if (tipo) params.set('tipo', tipo)
      api.get(`/peliculas?${params.toString()}`).then(({ data }) => setMovies(data))
    }
  }, [q, genero, tipo])

  return (
    <main className="page">
      <div className="page-title">
        <h1>Catálogo CineFlix</h1>
        <p>Busca por título, género, año, director o actor principal.</p>
      </div>

      <div className="filters">
        {generos.map(g => <button className={genero === g ? 'active' : ''} onClick={() => setGenero(g)} key={g}>{g}</button>)}
        <select value={tipo} onChange={e => setTipo(e.target.value)}>
          <option value="">Todos los productos</option>
          <option value="PELICULA">Películas</option>
          <option value="SERIE">Series</option>
          <option value="DOCUMENTAL">Documentales</option>
          <option value="CORTOMETRAJE">Cortometrajes</option>
          <option value="PREMIUM">Premium</option>
        </select>
      </div>

      {q && <h2 className="search-title">Resultados para: “{q}”</h2>}

      <div className="grid-catalog">
        {movies.map(movie => <MovieCard key={movie.id} pelicula={movie} />)}
      </div>
    </main>
  )
}
