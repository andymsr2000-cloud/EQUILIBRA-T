import { useMemo, useState } from 'react'
import Layout from '../components/Layout'
import { Ico } from '../components/Iconos'
import { useApp } from '../context/AppContext'
import { calcularImc, clasificarImc } from '../data/seed'

const META = {
  BAJO: {
    label: 'Bajo peso',
    color: 'text-sky-600',
    caja: 'bg-sky-50 text-sky-800',
    mensaje: 'El resultado está por debajo del rango recomendado.',
  },
  OPTIMO: {
    label: 'Óptimo',
    color: 'text-emerald-600',
    caja: 'bg-emerald-50 text-emerald-800',
    mensaje: 'El resultado se encuentra dentro del rango recomendado.',
  },
  MODERADO: {
    label: 'Sobrepeso',
    color: 'text-amber-600',
    caja: 'bg-amber-50 text-amber-800',
    mensaje: 'El resultado sugiere un riesgo moderado. Se recomienda seguimiento.',
  },
  CRITICO: {
    label: 'Crítico',
    color: 'text-rose-600',
    caja: 'bg-rose-50 text-rose-800',
    mensaje: 'El resultado indica riesgo alto. Derive a evaluación médica.',
  },
}

function posicionBarra(imc) {
  if (imc == null) return 18
  const min = 15
  const max = 37
  const pct = ((Math.min(max, Math.max(min, imc)) - min) / (max - min)) * 100
  return pct
}

export default function AntropometriaView() {
  const { guardarEvaluacion } = useApp()
  const [dni, setDni] = useState('')
  const [peso, setPeso] = useState('72')
  const [talla, setTalla] = useState('1.70')
  const [guardado, setGuardado] = useState(null)

  const imc = useMemo(() => calcularImc(Number(peso), Number(talla)), [peso, talla])
  const riesgo = imc == null ? null : clasificarImc(imc)
  const meta = riesgo ? META[riesgo] : null

  const enviar = (e) => {
    e.preventDefault()
    const registro = guardarEvaluacion({
      dni,
      pesoKg: Number(peso),
      tallaM: Number(talla),
    })
    setGuardado(registro)
  }

  return (
    <Layout
      kicker="EVALUACIÓN INDIVIDUAL"
      titulo="Ficha antropométrica"
      subtitulo="Registra datos básicos y obtén una clasificación inmediata."
    >
      <div className="grid grid-cols-[1.2fr_0.8fr] gap-5">
        <form onSubmit={enviar} className="rounded-2xl bg-white p-6 shadow-sm">
          <div className="flex items-center gap-3 rounded-xl bg-blue-50 px-4 py-3 text-sm text-blue-800">
            <span className="grid h-8 w-8 place-items-center rounded-full bg-white text-brand">⏱</span>
            <div>
              <p className="font-semibold">Registro ágil</p>
              <p className="text-xs text-blue-700/80">Tiempo estimado: menos de 2 minutos</p>
            </div>
          </div>

          <p className="mt-6 text-[11px] font-semibold tracking-[0.14em] text-slate-400">DATOS DEL EVALUADO</p>
          <h2 className="mt-1 text-lg font-bold">Información antropométrica</h2>
          <p className="text-sm text-slate-500">Completa los campos para calcular el IMC.</p>

          <label className="mt-5 block text-sm font-semibold">DNI</label>
          <div className="mt-2 flex items-center gap-2 rounded-xl border border-slate-200 px-3 py-2.5">
            <Ico.user className="h-4 w-4 text-slate-400" />
            <input
              value={dni}
              onChange={(e) => setDni(e.target.value.replace(/\D/g, '').slice(0, 8))}
              placeholder="8 dígitos"
              className="w-full text-sm outline-none"
              maxLength={8}
              required
            />
          </div>
          <p className="mt-1 text-xs text-slate-400">Documento Nacional de Identidad</p>

          <div className="mt-4 grid grid-cols-2 gap-4">
            <div>
              <label className="text-sm font-semibold">Peso (kg)</label>
              <input
                value={peso}
                onChange={(e) => setPeso(e.target.value)}
                type="number"
                step="0.1"
                min="1"
                className="mt-2 w-full rounded-xl border border-slate-200 px-3 py-2.5 text-sm outline-none"
                required
              />
            </div>
            <div>
              <label className="text-sm font-semibold">Talla (m)</label>
              <input
                value={talla}
                onChange={(e) => setTalla(e.target.value)}
                type="number"
                step="0.01"
                min="0.5"
                className="mt-2 w-full rounded-xl border border-slate-200 px-3 py-2.5 text-sm outline-none"
                required
              />
            </div>
          </div>

          <div className="mt-6 flex justify-end">
            <button type="submit" className="rounded-xl bg-brand px-5 py-2.5 text-sm font-semibold text-white">
              ✓ Guardar evaluación
            </button>
          </div>
          {guardado ? (
            <p className="mt-3 text-sm text-emerald-700">
              Evaluación #{guardado.id} guardada · IMC {guardado.imc} ({guardado.nivelRiesgo})
            </p>
          ) : null}
        </form>

        <aside className="rounded-2xl bg-white p-6 text-center shadow-sm">
          <p className="text-[11px] font-semibold tracking-[0.16em] text-slate-400">RESULTADO EN TIEMPO REAL</p>
          <h2 className="mt-2 text-lg font-semibold text-slate-700">Índice de Masa Corporal</h2>
          <p className="mt-4 text-6xl font-extrabold tracking-tight text-slate-900">
            {imc ?? '—'}
            <span className="ml-1 align-top text-base font-medium text-slate-400">kg/m²</span>
          </p>
          {meta ? (
            <p className={`mt-2 inline-flex items-center gap-1 text-sm font-semibold ${meta.color}`}>
              ✓ {meta.label}
            </p>
          ) : null}

          <div className="relative mt-8 h-2.5 overflow-visible rounded-full bg-gradient-to-r from-sky-400 via-emerald-400 via-50% via-yellow-400 to-rose-500">
            <span
              className="absolute -top-1 h-4 w-4 -translate-x-1/2 rounded-full border-2 border-white bg-slate-900 shadow"
              style={{ left: `${posicionBarra(imc)}%` }}
            />
          </div>
          <div className="mt-2 flex justify-between text-[11px] text-slate-400">
            <span>15</span>
            <span>18.5</span>
            <span>25</span>
            <span>30</span>
            <span>37+</span>
          </div>

          {meta ? (
            <div className={`mt-6 rounded-xl px-4 py-3 text-left text-sm ${meta.caja}`}>
              <p className="font-semibold">✓ {meta.label === 'Óptimo' ? 'Peso saludable' : meta.label}</p>
              <p className="mt-1 text-[13px] opacity-90">{meta.mensaje}</p>
            </div>
          ) : null}
          <p className="mt-6 text-[11px] text-slate-400">
            El IMC es un indicador referencial y no reemplaza una evaluación médica.
          </p>
        </aside>
      </div>
    </Layout>
  )
}
