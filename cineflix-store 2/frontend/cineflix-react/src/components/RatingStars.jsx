import { Star } from 'lucide-react'

export default function RatingStars({ rating = 0 }) {
  const value = Math.round(Number(rating) / 2)
  return (
    <div className="stars">
      {[1, 2, 3, 4, 5].map(n => <Star key={n} size={14} fill={n <= value ? 'currentColor' : 'none'} />)}
      <span>{Number(rating).toFixed(1)}</span>
    </div>
  )
}
