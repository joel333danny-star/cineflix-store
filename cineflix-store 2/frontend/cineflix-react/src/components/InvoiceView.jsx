import { money } from '../services/api'

export default function InvoiceView({ factura }) {
  if (!factura) return null
  return (
    <div className="invoice">
      <div className="invoice-top">
        <div>
          <h1>Factura electrónica</h1>
          <p>Folio: <strong>{factura.folio}</strong></p>
          <p>Fecha: {new Date(factura.fechaEmision).toLocaleString('es-MX')}</p>
        </div>
        <div className="invoice-logo">CineFlix</div>
      </div>

      <div className="invoice-grid">
        <div>
          <h3>Emisor</h3>
          <p>{factura.emisorNombre}</p>
          <p>RFC: {factura.emisorRfc}</p>
        </div>
        <div>
          <h3>Receptor</h3>
          <p>{factura.receptorNombre}</p>
          <p>{factura.receptorEmail}</p>
        </div>
      </div>

      <table className="invoice-table">
        <thead>
          <tr>
            <th>Descripción</th>
            <th>Tipo</th>
            <th>Cantidad</th>
            <th>Precio</th>
            <th>Total</th>
          </tr>
        </thead>
        <tbody>
          {factura.detalles?.map((d, i) => (
            <tr key={i}>
              <td>{d.descripcion}</td>
              <td>{d.tipoAdquisicion}</td>
              <td>{d.cantidad}</td>
              <td>{money(d.precioUnitario)}</td>
              <td>{money(d.totalLinea)}</td>
            </tr>
          ))}
        </tbody>
      </table>

      <div className="invoice-totals">
        <p><span>Subtotal</span><strong>{money(factura.subtotal)}</strong></p>
        <p><span>IVA</span><strong>{money(factura.impuestos)}</strong></p>
        <p className="grand"><span>Total</span><strong>{money(factura.total)}</strong></p>
      </div>
    </div>
  )
}
