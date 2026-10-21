import { useEffect, useState } from 'react'
import { Link, useParams } from 'react-router-dom'
import { Download, Printer } from 'lucide-react'
import { api } from '../services/api'
import InvoiceView from '../components/InvoiceView.jsx'

export default function Factura() {
  const { pedidoId } = useParams()
  const [factura, setFactura] = useState(null)

  useEffect(() => {
    api.get(`/facturas/pedido/${pedidoId}`).then(({ data }) => setFactura(data)).catch(console.error)
  }, [pedidoId])

  return (
    <main className="page">
      <div className="page-title row-title">
        <div>
          <h1>Compra completada</h1>
          <p>Tu factura fue generada automáticamente.</p>
        </div>
        <div className="invoice-actions">
          <button onClick={() => window.print()}><Printer /> Imprimir</button>
          <button onClick={() => window.print()}><Download /> Guardar PDF</button>
        </div>
      </div>
      <InvoiceView factura={factura} />
      <Link className="checkout-btn center" to="/biblioteca">Ir a mi biblioteca</Link>
    </main>
  )
}
