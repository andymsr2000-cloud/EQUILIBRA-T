import Layout from '../components/Layout'
import { Ico } from '../components/Iconos'
import { useApp } from '../context/AppContext'
import { clasificarFatiga } from '../data/seed'

const ETIQUETA = {
  BAJO: { label: 'Óptimo', color: 'text-emerald-600', bg: 'bg-emerald-50', ring: '#22c55e' },
  OPTIMO: { label: 'Óptimo', color: 'text-emerald-600', bg: 'bg-emerald-50', ring: '#22c55e' },
  MODERADO: { label: 'Riesgo moderado', color: 'text-amber-600', bg: 'bg-amber-50', ring: '#f59e0b' },
  CRITICO: { label: 'Riesgo crítico', color: 'text-rose-600', bg: 'bg-rose-50', ring: '#f43f5e' },
}

function Donut({ segmentos, total }) {
  const r = 54
  const c = 2 * Math.PI * r
  let offset = 0
  return (
    <svg viewBox="0 0 140 140" className="h-44 w-44 -rotate-90">
      <circle cx="70" cy="70" r={r} fill="none" stroke="#eef2f7" strokeWidth="16" />
      {segmentos.map((s) => {
        const dash = (s.valor / total) * c
        const el = (
          <circle
            key={s.color}
            cx="70"
            cy="70"
            r={r}
            fill="none"
            stroke={s.color}
            strokeWidth="16"
            strokeDasharray={`${dash} ${c - dash}`}
            strokeDashoffset={-offset}
            strokeLinecap="butt"
          />
        )
        offset += dash
        return el
      })}
    </svg>
  )
}

export default function DashboardView() {
  const { peritos, alertas, setVista } = useApp()
  const total = peritos.length
  const promedio =
    Math.round((peritos.reduce((acc, p) => acc + p.nivelFatiga, 0) / total) * 10) / 10
  const dist = {
    BAJO: peritos.filter((p) => clasificarFatiga(p.nivelFatiga) === 'BAJO').length,
    MODERADO: peritos.filter((p) => clasificarFatiga(p.nivelFatiga) === 'MODERADO').length,
    CRITICO: peritos.filter((p) => clasificarFatiga(p.nivelFatiga) === 'CRITICO').length,
  }
  const criticas = dist.CRITICO

  return (
    <Layout
      kicker="RESUMEN GENERAL"
      titulo="Bienestar del equipo"
      subtitulo="Indicadores actualizados del personal pericial."
    >
      <div className="grid grid-cols-4 gap-4">
        <Kpi
          icon={Ico.user}
          badge="100%"
          badgeClass="text-slate-400"
          label="Personal evaluado"
          valor={String(total).padStart(2, '0')}
          pie={`de ${total} colaboradores`}
        />
        <Kpi
          icon={Ico.pulse}
          badge="Estable"
          badgeClass="text-emerald-600"
          label="Fatiga promedio"
          valor={`${promedio}%`}
          pie="Datos semilla institucionales"
        />
        <Kpi
          icon={Ico.folder}
          badge={`${total}`}
          badgeClass="text-slate-500"
          label="Casos registrados"
          valor={String(total)}
          pie="colaboradores con ficha activa"
        />
        <Kpi
          icon={Ico.alert}
          badge="Revisar"
          badgeClass="text-rose-500"
          label="Alertas activas"
          valor={String(alertas.length).padStart(2, '0')}
          pie={`${criticas} ${criticas === 1 ? 'requiere' : 'requieren'} atención`}
        />
      </div>

      <div className="mt-5 grid grid-cols-[1.35fr_1fr] gap-4">
        <section className="rounded-2xl bg-white p-6 shadow-sm">
          <div className="flex items-center justify-between">
            <div>
              <p className="text-[11px] font-semibold tracking-[0.14em] text-slate-400">DISTRIBUCIÓN ACTUAL</p>
              <h2 className="mt-1 text-lg font-bold">Estado de salud ocupacional</h2>
            </div>
            <button type="button" onClick={() => setVista('reportes')} className="text-sm font-medium text-brand">
              Ver detalle ›
            </button>
          </div>
          <div className="mt-4 flex items-center gap-8">
            <div className="relative">
              <Donut
                total={total}
                segmentos={[
                  { valor: dist.BAJO, color: '#22c55e' },
                  { valor: dist.MODERADO, color: '#eab308' },
                  { valor: dist.CRITICO, color: '#f43f5e' },
                ]}
              />
              <div className="absolute inset-0 grid place-items-center rotate-0">
                <div className="text-center">
                  <p className="text-3xl font-bold">{total}</p>
                  <p className="text-[11px] text-slate-400">evaluados</p>
                </div>
              </div>
            </div>
            <ul className="flex-1 space-y-4">
              <FilaLeyenda color="text-emerald-600" icon="✓" label="Óptimo" n={dist.BAJO} pct={Math.round((dist.BAJO / total) * 100)} />
              <FilaLeyenda color="text-amber-600" icon="△" label="Riesgo moderado" n={dist.MODERADO} pct={Math.round((dist.MODERADO / total) * 100)} />
              <FilaLeyenda color="text-rose-600" icon="🛡" label="Riesgo crítico" n={dist.CRITICO} pct={Math.round((dist.CRITICO / total) * 100)} />
            </ul>
          </div>
          <p className="mt-4 text-sm text-emerald-700">Tendencia: mapa construido con los 4 registros semilla de Tacna.</p>
        </section>

        <section className="rounded-2xl bg-white p-6 shadow-sm">
          <div className="flex items-center justify-between">
            <div>
              <p className="text-[11px] font-semibold tracking-[0.14em] text-slate-400">SEGUIMIENTO</p>
              <h2 className="mt-1 text-lg font-bold">Alertas recientes</h2>
            </div>
            <span className="rounded-full bg-rose-50 px-2 py-0.5 text-xs font-semibold text-rose-600">
              {alertas.length} activas
            </span>
          </div>
          <ul className="mt-4 space-y-3">
            {alertas.map((a) => {
              const estilo = ETIQUETA[a.nivel]
              return (
                <li key={a.id} className="flex items-center justify-between rounded-xl border border-slate-100 p-3">
                  <div className="flex items-center gap-3">
                    <div className="grid h-10 w-10 place-items-center rounded-full bg-slate-100 text-xs font-bold">
                      {a.iniciales}
                    </div>
                    <div>
                      <p className="text-sm font-semibold">{a.nombre}</p>
                      <p className="text-xs text-slate-400">{a.detalle}</p>
                      <p className="text-[11px] text-slate-400">{a.tiempo}</p>
                    </div>
                  </div>
                  <span className={`rounded-full px-2 py-1 text-[11px] font-semibold ${estilo.bg} ${estilo.color}`}>
                    {estilo.label}
                  </span>
                </li>
              )
            })}
          </ul>
          <button
            type="button"
            onClick={() => setVista('reportes')}
            className="mt-4 w-full rounded-xl border border-slate-200 py-2.5 text-sm font-medium text-slate-600"
          >
            Revisar todas las alertas ›
          </button>
        </section>
      </div>
    </Layout>
  )
}

function Kpi({ icon: Icon, badge, badgeClass, label, valor, pie }) {
  return (
    <article className="rounded-2xl bg-white p-5 shadow-sm">
      <div className="flex items-start justify-between">
        <div className="grid h-10 w-10 place-items-center rounded-xl bg-slate-50 text-slate-500">
          <Icon className="h-5 w-5" />
        </div>
        <span className={`text-xs font-semibold ${badgeClass}`}>{badge}</span>
      </div>
      <p className="mt-6 text-sm text-slate-500">{label}</p>
      <p className="mt-1 text-4xl font-bold tracking-tight">{valor}</p>
      <p className="mt-1 text-xs text-slate-400">{pie}</p>
    </article>
  )
}

function FilaLeyenda({ color, icon, label, n, pct }) {
  return (
    <li className="flex items-center justify-between">
      <span className={`flex items-center gap-2 text-sm ${color}`}>
        <span>{icon}</span>
        {label}
        <span className="text-slate-400">{pct}% del personal</span>
      </span>
      <span className="text-lg font-semibold text-slate-800">{String(n).padStart(2, '0')}</span>
    </li>
  )
}
