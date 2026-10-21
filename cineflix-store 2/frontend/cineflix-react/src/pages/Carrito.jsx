import { Link } from 'react-router-dom'
import { Trash2 } from 'lucide-react'
import { useCart } from '../context/CartContext.jsx'
import { money } from '../services/api'

export default function Carrito() {
  const { cart, updateQuantity, removeItem } = useCart()
  const iva = Number(cart.subtotal || 0) * 0.16
  const total = Number(cart.subtotal || 0) + iva

  return (
    <main className="page">
      <div className="page-title">
        <h1>Carrito de compras</h1>
        <p>Actualiza cantidades, elimina productos y continúa al pago.</p>
      </div>

      <div className="cart-page-layout">
        <section className="cart-list">
          {cart.items?.length === 0 && <p className="muted">Tu carrito está vacío.</p>}
          {cart.items?.map(item => (
            <div className="cart-line" key={item.itemId}>
              <img src={item.posterUrl} alt={item.titulo} />
              <div className="cart-line-info">
                <h3>{item.titulo}</h3>
                <p>{item.tipoAdquisicion} · {money(item.precioUnitario)}</p>
              </div>
              <div className="qty large">
                <button onClick={() => updateQuantity(item.itemId, item.cantidad - 1)}>-</button>
                <span>{item.cantidad}</span>
                <button onClick={() => updateQuantity(item.itemId, item.cantidad + 1)}>+</button>
              </div>
              <strong>{money(item.totalLinea)}</strong>
              <button className="trash" onClick={() => removeItem(item.itemId)}><Trash2 /></button>
            </div>
          ))}
        </section>

        <aside className="summary-card">
          <h2>Resumen</h2>
          <p><span>Subtotal</span><strong>{money(cart.subtotal)}</strong></p>
          <p><span>IVA 16%</span><strong>{money(iva)}</strong></p>
          <p className="grand"><span>Total</span><strong>{money(total)}</strong></p>
          <Link className="checkout-btn" to="/checkout">Continuar al pago</Link>
        </aside>
      </div>
    </main>
  )
}
