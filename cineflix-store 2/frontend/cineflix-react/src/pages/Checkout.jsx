import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { CreditCard } from 'lucide-react'
import { api, USER_ID, money } from '../services/api'
import { useCart } from '../context/CartContext.jsx'

export default function Checkout() {
  const navigate = useNavigate()
  const { cart, loadCart } = useCart()
  const [form, setForm] = useState({
    receptorNombre: 'Cliente Demo',
    receptorEmail: 'cliente@cineflix.com',
    formaPago: 'TARJETA',
    tarjeta: '',
    vencimiento: '',
    cvv: ''
  })
  const [loading, setLoading] = useState(false)

  const iva = Number(cart.subtotal || 0) * 0.16
  const total = Number(cart.subtotal || 0) + iva

  async function pagar(e) {
    e.preventDefault()
    if (!cart.items?.length) return alert('El carrito está vacío')
    setLoading(true)
    try {
      const { data } = await api.post('/pedidos/checkout', {
        usuarioId: USER_ID,
        receptorNombre: form.receptorNombre,
        receptorEmail: form.receptorEmail,
        formaPago: form.formaPago
      })
      await loadCart()
      navigate(`/factura/${data.pedidoId}`)
    } catch (error) {
      alert(error.response?.data?.error || 'No se pudo procesar el pago')
    } finally {
      setLoading(false)
    }
  }

  return (
    <main className="page checkout-page">
      <div className="page-title">
        <h1>Pago seguro</h1>
        <p>Registra una forma de pago para generar el pedido y la factura.</p>
      </div>

      <form className="payment-card" onSubmit={pagar}>
        <h2><CreditCard /> Datos de pago</h2>
        <label>Nombre del receptor</label>
        <input value={form.receptorNombre} onChange={e => setForm({ ...form, receptorNombre: e.target.value })} required />
        <label>Correo del receptor</label>
        <input type="email" value={form.receptorEmail} onChange={e => setForm({ ...form, receptorEmail: e.target.value })} required />
        <label>Forma de pago</label>
        <select value={form.formaPago} onChange={e => setForm({ ...form, formaPago: e.target.value })}>
          <option value="TARJETA">Tarjeta</option>
          <option value="PAYPAL">PayPal</option>
          <option value="TRANSFERENCIA">Transferencia</option>
          <option value="OXXO">OXXO</option>
        </select>
        <div className="fake-card-fields">
          <input placeholder="Número de tarjeta demo" value={form.tarjeta} onChange={e => setForm({ ...form, tarjeta: e.target.value })} />
          <input placeholder="MM/AA" value={form.vencimiento} onChange={e => setForm({ ...form, vencimiento: e.target.value })} />
          <input placeholder="CVV" value={form.cvv} onChange={e => setForm({ ...form, cvv: e.target.value })} />
        </div>
        <div className="summary-mini">
          <p><span>Subtotal</span><strong>{money(cart.subtotal)}</strong></p>
          <p><span>IVA</span><strong>{money(iva)}</strong></p>
          <p className="grand"><span>Total</span><strong>{money(total)}</strong></p>
        </div>
        <button className="main-btn" disabled={loading}>{loading ? 'Procesando...' : 'Pagar y generar factura'}</button>
      </form>
    </main>
  )
}
