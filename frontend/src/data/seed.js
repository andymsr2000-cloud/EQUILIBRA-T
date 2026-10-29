export const CLAVE_DEMO = 'EquilibraT2025'

export const ROLES = [
  { id: 'PERITO', label: 'Perito', icon: 'user' },
  { id: 'RRHH_SALUD', label: 'RRHH', icon: 'users' },
  { id: 'JEFATURA', label: 'Jefatura', icon: 'briefcase' },
]

export const PERITOS = [
  {
    id: 1,
    cip: '10000001',
    dni: '40000001',
    nombre: 'Crnl. Jorge Velásquez',
    departamento: 'Jefatura Criminalística',
    rol: 'JEFATURA',
    nivelFatiga: 0.0,
    iniciales: 'JV',
  },
  {
    id: 2,
    cip: '20000001',
    dni: '40000002',
    nombre: 'Rosa Linares',
    departamento: 'Biología Forense',
    rol: 'PERITO',
    nivelFatiga: 65.0,
    iniciales: 'RL',
  },
  {
    id: 3,
    cip: '20000002',
    dni: '40000003',
    nombre: 'Marcos Castillo',
    departamento: 'Balística',
    rol: 'PERITO',
    nivelFatiga: 45.0,
    iniciales: 'MC',
  },
  {
    id: 4,
    cip: '20000003',
    dni: '40000004',
    nombre: 'Elena Ríos',
    departamento: 'Química',
    rol: 'PERITO',
    nivelFatiga: 20.0,
    iniciales: 'ER',
  },
]

export function clasificarFatiga(fatiga) {
  if (fatiga >= 60) return 'CRITICO'
  if (fatiga >= 30) return 'MODERADO'
  return 'BAJO'
}

export function clasificarImc(imc) {
  if (imc < 18.5) return 'BAJO'
  if (imc <= 24.9) return 'OPTIMO'
  if (imc <= 29.9) return 'MODERADO'
  return 'CRITICO'
}

export function calcularImc(pesoKg, tallaM) {
  if (!pesoKg || !tallaM) return null
  return Math.round((pesoKg / (tallaM * tallaM)) * 10) / 10
}

export const ALERTAS_INICIALES = [
  {
    id: 1,
    peritoId: 2,
    nombre: 'Rosa Linares',
    iniciales: 'RL',
    detalle: 'Fatiga crítica · Biología Forense',
    tiempo: 'Hace 18 min',
    nivel: 'CRITICO',
  },
  {
    id: 2,
    peritoId: 3,
    nombre: 'Marcos Castillo',
    iniciales: 'MC',
    detalle: 'Fatiga moderada · Balística',
    tiempo: 'Hace 2 h',
    nivel: 'MODERADO',
  },
]
