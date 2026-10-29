import { useEffect, useMemo, useState } from 'react'
import Layout from '../components/Layout'
import { Ico } from '../components/Iconos'

const PASOS = [
  {
    id: 1,
    titulo: 'Movilidad cervical',
    detalle: 'Inclinaciones suaves · 45 seg',
    hint: 'Lleva la oreja hacia cada hombro, sin forzar.',
    icon: Ico.pulse,
  },
  {
    id: 2,
    titulo: 'Visual 20-20-20',
    detalle: 'Descanso de enfoque · 60 seg',
    hint: 'Mira a 6 metros de distancia durante 20 segundos.',
    icon: Ico.eye,
  },
  {
    id: 3,
    titulo: 'Muñecas y manos',
    detalle: 'Rotación articular · 45 seg',
    hint: 'Haz círculos lentos y estira cada palma.',
    icon: Ico.spark,
  },
]

const DURACION = 180

function formato(seg) {
  const m = Math.floor(seg / 60)
  const s = seg % 60
  return `${m}:${String(s).padStart(2, '0')}`
}

export default function PausasActivasView() {
  const [restante, setRestante] = useState(DURACION)
  const [corriendo, setCorriendo] = useState(false)
  const [hechos, setHechos] = useState([])
  const [confirmada, setConfirmada] = useState(false)

  useEffect(() => {
    if (!corriendo) return undefined
    const id = setInterval(() => {
      setRestante((prev) => {
        if (prev <= 1) {
          setCorriendo(false)
          return 0
        }
        return prev - 1
      })
    }, 1000)
    return () => clearInterval(id)
  }, [corriendo])

  const progreso = ((DURACION - restante) / DURACION) * 100
  const radio = 78
  const circ = 2 * Math.PI * radio
  const dash = (progreso / 100) * circ

  const togglePaso = (id) => {
    setHechos((prev) => (prev.includes(id) ? prev.filter((x) => x !== id) : [...prev, id]))
  }

  const listos = hechos.length
  const puedeConfirmar = listos === 3 && restante === 0

  const estadoReloj = useMemo(() => {
    if (restante === 0) return 'Rutina completada'
    if (corriendo) return 'En curso'
    return 'Listo para comenzar'
  }, [restante, corriendo])

  return (
    <Layout
      kicker="PREVENCIÓN ERGONÓMICA"
      titulo="Pausa activa guiada"
      subtitulo="Tres minutos para recuperar el enfoque y reducir la tensión."
    >
      <div className="grid grid-cols-[0.85fr_1.15fr] gap-5">
        <section className="rounded-3xl bg-[#0A1128] p-8 text-center text-white shadow-lg">
          <p className="text-[11px] font-semibold tracking-[0.2em] text-amber-400">RUTINA DIARIA</p>
          <div className="relative mx-auto mt-6 h-56 w-56">
            <svg viewBox="0 0 180 180" className="h-full w-full -rotate-90">
              <circle cx="90" cy="90" r={radio} fill="none" stroke="#1e293b" strokeWidth="10" />
              <circle
                cx="90"
                cy="90"
                r={radio}
                fill="none"
                stroke="#f59e0b"
                strokeWidth="10"
                strokeLinecap="round"
                strokeDasharray={`${dash} ${circ}`}
              />
            </svg>
            <div className="absolute inset-0 grid place-items-center">
              <div>
                <div className="mx-auto mb-1 grid h-8 w-8 place-items-center rounded-full bg-amber-400/20 text-amber-400">
                  ⏱
                </div>
                <p className="text-5xl font-bold tracking-tight">{formato(restante)}</p>
                <p className="text-xs text-slate-400">{estadoReloj}</p>
              </div>
            </div>
          </div>
          <h2 className="mt-4 text-xl font-semibold">Tu cuerpo también necesita una pausa</h2>
          <p className="mt-2 text-sm text-slate-400">
            Sigue cada ejercicio con movimientos lentos. Detente si sientes alguna molestia.
          </p>
          <button
            type="button"
            onClick={() => {
              if (restante === 0) {
                setRestante(DURACION)
                setCorriendo(true)
                setConfirmada(false)
                return
              }
              setCorriendo((v) => !v)
            }}
            className="mt-6 w-full rounded-xl bg-brand py-3 text-sm font-semibold"
          >
            {corriendo ? 'Pausar rutina' : restante === 0 ? 'Reiniciar rutina' : 'Iniciar rutina de 3 min'}
          </button>
        </section>

        <section>
          <div className="mb-3 flex items-center justify-between">
            <div>
              <p className="text-[11px] font-semibold tracking-[0.14em] text-slate-400">3 MOVIMIENTOS</p>
              <h2 className="text-lg font-bold">Ejercicios de la rutina</h2>
            </div>
            <p className="text-sm text-slate-400">{listos} de 3 completados</p>
          </div>
          <ul className="space-y-3">
            {PASOS.map((paso) => {
              const Icon = paso.icon
              const hecho = hechos.includes(paso.id)
              return (
                <li key={paso.id}>
                  <button
                    type="button"
                    onClick={() => togglePaso(paso.id)}
                    className={`flex w-full items-center gap-4 rounded-2xl border bg-white p-4 text-left shadow-sm ${
                      hecho ? 'border-emerald-200' : 'border-slate-100'
                    }`}
                  >
                    <span className="w-8 text-xs font-semibold text-slate-400">
                      {String(paso.id).padStart(2, '0')}
                    </span>
                    <span className="grid h-11 w-11 place-items-center rounded-xl bg-blue-50 text-brand">
                      <Icon className="h-5 w-5" />
                    </span>
                    <span className="flex-1">
                      <span className="block font-semibold">{paso.titulo}</span>
                      <span className="block text-xs text-brand">{paso.detalle}</span>
                      <span className="block text-xs text-slate-400">{paso.hint}</span>
                    </span>
                    <span
                      className={`h-5 w-5 rounded-md border ${hecho ? 'border-emerald-500 bg-emerald-500' : 'border-slate-300'}`}
                    />
                  </button>
                </li>
              )
            })}
          </ul>
          <div className="mt-4 flex justify-end">
            <button
              type="button"
              disabled={!puedeConfirmar}
              onClick={() => setConfirmada(true)}
              className="rounded-xl border border-slate-200 bg-white px-4 py-2.5 text-sm font-medium text-slate-600 disabled:opacity-40"
            >
              ✓ Confirmar pausa realizada
            </button>
          </div>
          {confirmada ? (
            <p className="mt-3 text-right text-sm text-emerald-700">Pausa registrada en la sesión actual.</p>
          ) : null}
        </section>
      </div>
    </Layout>
  )
}
