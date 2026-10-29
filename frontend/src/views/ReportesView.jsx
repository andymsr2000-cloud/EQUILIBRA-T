import { useMemo, useState } from 'react'
import Layout from '../components/Layout'
import { useApp } from '../context/AppContext'
import { clasificarFatiga } from '../data/seed'

const ESTILO = {
  BAJO: {
    card: 'bg-emerald-50',
    text: 'text-emerald-700',
    label: 'Nivel bajo',
  },
  MODERADO: {
    card: 'bg-amber-50',
    text: 'text-amber-700',
    label: 'Riesgo moderado',
  },
  CRITICO: {
    card: 'bg-rose-50',
    text: 'text-rose-700',
    label: 'Riesgo crítico',
  },
}

function exportarExcel(filas) {
  const cabecera = ['CIP', 'Nombre', 'Departamento', 'Rol', 'Fatiga (%)', 'Nivel de riesgo']
  const lineas = [
    cabecera.join(','),
    ...filas.map((p) =>
      [p.cip, `"${p.nombre}"`, `"${p.departamento}"`, p.rol, p.nivelFatiga, clasificarFatiga(p.nivelFatiga)].join(','),
    ),
  ]
  const blob = new Blob([`\uFEFF${lineas.join('\n')}`], { type: 'text/csv;charset=utf-8;' })
  const url = URL.createObjectURL(blob)
  const a = document.createElement('a')
  a.href = url
  a.download = 'reporte-fatiga-equilibra-t.csv'
  a.click()
  URL.revokeObjectURL(url)
}

export default function ReportesView() {
  const { peritos } = useApp()
  const [area, setArea] = useState('TODAS')
  const areas = useMemo(
    () => ['TODAS', ...new Set(peritos.map((p) => p.departamento))],
    [peritos],
  )
  const filtrados = area === 'TODAS' ? peritos : peritos.filter((p) => p.departamento === area)

  return (
    <Layout
      kicker="ANÁLISIS INSTITUCIONAL"
      titulo="Reportes ejecutivos"
      subtitulo="Identifica tendencias y prioriza acciones de bienestar."
    >
      <div className="flex flex-wrap items-end justify-between gap-4 rounded-2xl bg-white p-5 shadow-sm">
        <div className="flex gap-4">
          <label className="text-sm">
            <span className="mb-1 block font-semibold text-slate-700">Área pericial</span>
            <select
              value={area}
              onChange={(e) => setArea(e.target.value)}
              className="min-w-[200px] rounded-xl border border-slate-200 px-3 py-2.5 text-sm"
            >
              {areas.map((a) => (
                <option key={a} value={a}>
                  {a === 'TODAS' ? 'Todas las áreas' : a}
                </option>
              ))}
            </select>
          </label>
          <label className="text-sm">
            <span className="mb-1 block font-semibold text-slate-700">Periodo</span>
            <select className="min-w-[160px] rounded-xl border border-slate-200 px-3 py-2.5 text-sm">
              <option>Octubre 2026</option>
            </select>
          </label>
        </div>
        <div className="flex gap-2">
          <button
            type="button"
            onClick={() => exportarExcel(filtrados)}
            className="rounded-xl border border-slate-200 px-4 py-2.5 text-sm font-medium"
          >
            ↓ Exportar Excel
          </button>
          <button 
          type="button" 
          onClick={() => window.print()}
          className="rounded-xl bg-brand px-4 py-2.5 text-sm font-semibold text-white"
          >
  Exportar PDF
</button>
        </div>
      </div>

      <section className="mt-5 rounded-2xl bg-white p-6 shadow-sm">
        <div className="flex items-start justify-between">
          <div>
            <p className="text-[11px] font-semibold tracking-[0.14em] text-brand">ÍNDICE DE FATIGA</p>
            <h2 className="mt-1 text-lg font-bold">Mapa de calor por área pericial</h2>
            <p className="text-sm text-slate-500">Porcentaje de fatiga de los colaboradores semilla.</p>
          </div>
          <div className="flex items-center gap-3 text-xs text-slate-500">
            <span className="flex items-center gap-1">
              <i className="h-2 w-2 rounded-full bg-emerald-500" /> Bajo (0-29%)
            </span>
            <span className="flex items-center gap-1">
              <i className="h-2 w-2 rounded-full bg-amber-400" /> Moderado (30-59%)
            </span>
            <span className="flex items-center gap-1">
              <i className="h-2 w-2 rounded-full bg-rose-500" /> Crítico (60%+)
            </span>
          </div>
        </div>

        <div className="mt-6 grid grid-cols-3 gap-4">
          {filtrados.map((p) => {
            const nivel = clasificarFatiga(p.nivelFatiga)
            const estilo = ESTILO[nivel]
            return (
              <article key={p.id} className={`relative min-h-[140px] rounded-2xl p-5 ${estilo.card}`}>
                <p className="text-4xl font-bold">{p.nivelFatiga}%</p>
                <p className="mt-6 text-sm font-semibold text-slate-800">{p.departamento}</p>
                <p className={`text-sm ${estilo.text}`}>{p.nombre}</p>
                <p className={`text-xs font-medium ${estilo.text}`}>{estilo.label}</p>
              </article>
            )
          })}
        </div>
      </section>
    </Layout>
  )
}
