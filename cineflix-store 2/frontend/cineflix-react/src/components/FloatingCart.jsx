import { Link } from 'react-router-dom'
import { ShoppingCart, X, Trash2 } from 'lucide-react'
import { useCart } from '../context/CartContext.jsx'
import { money } from '../services/api'

export default function FloatingCart() {
  const { cart, openCart, setOpenCart, updateQuantity, removeItem } = useCart()

  return (
    <>
      <button className="floating-cart-button" onClick={() => setOpenCart(true)}>
        <ShoppingCart />
        <span>{cart.totalItems || 0}</span>
      </button>

      <aside className={`cart-drawer ${openCart ? 'show' : ''}`}>
        <div className="drawer-head">
          <h2>Tu carrito</h2>
          <button onClick={() => setOpenCart(false)}><X /></button>
        </div>

        {cart.items?.length === 0 && <p className="muted">Todavía no agregas películas.</p>}

        <div className="drawer-items">
          {cart.items?.map(item => (
            <div className="drawer-item" key={item.itemId}>
              <img src={item.posterUrl} alt={item.titulo} />
              <div>
                <strong>{item.titulo}</strong>
                <small>{item.tipoAdquisicion} · {money(item.precioUnitario)}</small>
                <div className="qty">
                  <button onClick={() => updateQuantity(item.itemId, item.cantidad - 1)}>-</button>
                  <span>{item.cantidad}</span>
                  <button onClick={() => updateQuantity(item.itemId, item.cantidad + 1)}>+</button>
                  <button className="trash" onClick={() => removeItem(item.itemId)}><Trash2 size={15} /></button>
                </div>
              </div>
            </div>
          ))}
        </div>

        <div className="drawer-total">
          <span>Subtotal</span>
          <strong>{money(cart.subtotal)}</strong>
        </div>
        <Link className="checkout-btn" to="/checkout" onClick={() => setOpenCart(false)}>Ir a pagar</Link>
        <Link className="ghost-link" to="/carrito" onClick={() => setOpenCart(false)}>Ver carrito completo</Link>
      </aside>
    </>
  )
}
