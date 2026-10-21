import MovieCard from './MovieCard.jsx'

export default function MovieCarousel({ title, movies = [] }) {
  if (!movies.length) return null
  return (
    <section className="carousel-section">
      <div className="section-head">
        <h2>{title}</h2>
        <span>VER TODO ›</span>
      </div>
      <div className="movie-row">
        {movies.map(movie => <MovieCard key={movie.id} pelicula={movie} />)}
      </div>
    </section>
  )
}
