import { Route, Routes } from 'react-router-dom'
import Navbar from './components/Navbar.jsx'
import FloatingCart from './components/FloatingCart.jsx'
import Home from './pages/Home.jsx'
import Catalogo from './pages/Catalogo.jsx'
import DetallePelicula from './pages/DetallePelicula.jsx'
import Carrito from './pages/Carrito.jsx'
import Checkout from './pages/Checkout.jsx'
import Factura from './pages/Factura.jsx'
import Biblioteca from './pages/Biblioteca.jsx'
import AdminDashboard from './pages/AdminDashboard.jsx'

export default function App() {
  return (
    <>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/catalogo" element={<Catalogo />} />
        <Route path="/pelicula/:id" element={<DetallePelicula />} />
        <Route path="/carrito" element={<Carrito />} />
        <Route path="/checkout" element={<Checkout />} />
        <Route path="/factura/:pedidoId" element={<Factura />} />
        <Route path="/biblioteca" element={<Biblioteca />} />
        <Route path="/admin" element={<AdminDashboard />} />
      </Routes>
      <FloatingCart />
    </>
  )
}
