import { X } from 'lucide-react'

export default function TrailerModal({ url, onClose }) {
  if (!url) return null
  return (
    <div className="modal-backdrop" onClick={onClose}>
      <div className="trailer-modal" onClick={e => e.stopPropagation()}>
        <button className="modal-close" onClick={onClose}><X /></button>
        <iframe src={url} title="Trailer" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowFullScreen />
      </div>
    </div>
  )
}
