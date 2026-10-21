import axios from 'axios'

export const API_URL = 'http://localhost:8080/api'
export const USER_ID = Number(localStorage.getItem('cineflix_user_id') || 1)

export const api = axios.create({
  baseURL: API_URL,
  headers: {
    'Content-Type': 'application/json'
  }
})

export function money(value) {
  return new Intl.NumberFormat('es-MX', {
    style: 'currency',
    currency: 'MXN'
  }).format(value || 0)
}
