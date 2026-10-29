import { createContext, useContext, useMemo, useState } from 'react'
import { ALERTAS_INICIALES, CLAVE_DEMO, PERITOS, clasificarImc, calcularImc } from '../data/seed'

const AppContext = createContext(null)

export function AppProvider({ children }) {
  const [usuario, setUsuario] = useState(null)
  const [vista, setVista] = useState('resumen')
  const [peritos] = useState(PERITOS)
  const [alertas, setAlertas] = useState(ALERTAS_INICIALES)
  const [evaluaciones, setEvaluaciones] = useState([])
  const [errorAuth, setErrorAuth] = useState('')

  const login = ({ cip, password, rol }) => {
    setErrorAuth('')
    const encontrado = peritos.find((p) => p.cip === cip)
    if (!encontrado || password !== CLAVE_DEMO) {
      setErrorAuth('Credenciales institucionales inválidas')
      return false
    }
    if (encontrado.rol !== rol && encontrado.rol !== 'ADMIN') {
      setErrorAuth('El rol seleccionado no corresponde al usuario')
      return false
    }
    setUsuario(encontrado)
    setVista('resumen')
    return true
  }

  const logout = () => {
    setUsuario(null)
    setVista('resumen')
  }

  const guardarEvaluacion = ({ dni, pesoKg, tallaM }) => {
    const imc = calcularImc(pesoKg, tallaM)
    const registro = {
      id: evaluaciones.length + 1,
      dni,
      pesoKg,
      tallaM,
      imc,
      nivelRiesgo: clasificarImc(imc),
      fecha: new Date().toISOString(),
    }
    setEvaluaciones((prev) => [registro, ...prev])
    return registro
  }

  const value = useMemo(
    () => ({
      usuario,
      vista,
      setVista,
      peritos,
      alertas,
      setAlertas,
      evaluaciones,
      errorAuth,
      login,
      logout,
      guardarEvaluacion,
    }),
    [usuario, vista, peritos, alertas, evaluaciones, errorAuth],
  )

  return <AppContext.Provider value={value}>{children}</AppContext.Provider>
}

export function useApp() {
  const ctx = useContext(AppContext)
  if (!ctx) throw new Error('useApp debe usarse dentro de AppProvider')
  return ctx
}
