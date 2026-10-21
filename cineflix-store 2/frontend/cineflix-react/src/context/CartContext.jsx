import { createContext, useContext, useEffect, useState } from 'react'
import { api, USER_ID } from '../services/api'

const CartContext = createContext(null)

export function CartProvider({ children }) {
  const [cart, setCart] = useState({ items: [], subtotal: 0, totalItems: 0 })
  const [openCart, setOpenCart] = useState(false)
  const [loading, setLoading] = useState(false)

  async function loadCart() {
    try {
      const { data } = await api.get(`/carrito/${USER_ID}`)
      setCart(data)
    } catch (error) {
      console.error(error)
    }
  }

  async function addToCart(peliculaId, tipoAdquisicion = 'RENTA') {
    setLoading(true)
    try {
      const { data } = await api.post('/carrito/agregar', {
        usuarioId: USER_ID,
        peliculaId,
        tipoAdquisicion
      })
      setCart(data)
      setOpenCart(true)
    } finally {
      setLoading(false)
    }
  }

  async function updateQuantity(itemId, cantidad) {
    const { data } = await api.put('/carrito/cantidad', { itemId, cantidad })
    setCart(data)
  }

  async function removeItem(itemId) {
    const { data } = await api.delete(`/carrito/item/${itemId}`)
    setCart(data)
  }

  useEffect(() => {
    loadCart()
  }, [])

  return (
    <CartContext.Provider value={{ cart, openCart, setOpenCart, loadCart, addToCart, updateQuantity, removeItem, loading }}>
      {children}
    </CartContext.Provider>
  )
}

export function useCart() {
  return useContext(CartContext)
}
