import { Ico, LogoEscudo } from './Iconos'
import { useApp } from '../context/AppContext'

const NAV = [
  { id: 'resumen', label: 'Resumen', icon: Ico.grid },
  { id: 'antropometria', label: 'Ficha antropométrica', icon: Ico.ruler },
  { id: 'pausas', label: 'Pausas activas', icon: Ico.pause },
  { id: 'reportes', label: 'Reportes ejecutivos', icon: Ico.report },
]

function fechaLarga() {
  return new Date().toLocaleDateString('es-PE', {
    weekday: 'long',
    day: 'numeric',
    month: 'long',
    year: 'numeric',
  })
}

export default function Layout({ children, kicker, titulo, subtitulo }) {
  const { vista, setVista, usuario, logout } = useApp()
  const iniciales = usuario?.iniciales ?? 'JV'

  return (
    <div className="flex min-h-screen bg-[#f4f6fb]">
      <aside className="sticky top-0 flex h-screen w-[248px] shrink-0 flex-col bg-[#0A1128] text-white">
        <div className="flex items-center gap-3 px-5 py-6">
          <LogoEscudo className="h-11 w-11" />
          <div>
            <p className="text-[15px] font-extrabold tracking-wide">EQUILIBRA-T</p>
            <p className="text-[11px] text-slate-400">Criminalística PNP · Tacna</p>
          </div>
        </div>
        <p className="px-6 pb-2 text-[10px] font-semibold tracking-[0.16em] text-slate-500">
          MENÚ PRINCIPAL
        </p>
        <nav className="flex-1 space-y-1 px-3">
          {NAV.map((item) => {
            const activo = vista === item.id
            const Icon = item.icon
            return (
              <button
                key={item.id}
                type="button"
                onClick={() => setVista(item.id)}
                className={`flex w-full items-center gap-3 rounded-xl px-3 py-2.5 text-left text-sm transition ${
                  activo ? 'bg-brand font-semibold text-white' : 'text-slate-300 hover:bg-white/5'
                }`}
              >
                <Icon className="h-4.5 w-4.5 h-[18px] w-[18px]" />
                {item.label}
              </button>
            )
          })}
        </nav>
        <div className="px-4 pb-3">
          <div className="rounded-2xl bg-[#151d38] p-4">
            <div className="mb-2 flex items-center gap-2 text-amber-300">
              <Ico.heart className="h-4 w-4" />
              <span className="text-sm font-semibold">¿Necesitas apoyo?</span>
            </div>
            <p className="text-[11px] text-slate-400">Bienestar PNP · Anexo 118</p>
          </div>
          <button
            type="button"
            onClick={logout}
            className="mt-3 flex w-full items-center gap-2 px-2 py-2 text-sm text-slate-400 hover:text-white"
          >
            <Ico.logout className="h-4 w-4" />
            Cerrar sesión
          </button>
        </div>
      </aside>

      <div className="min-w-0 flex-1">
        <header className="flex items-center justify-end gap-6 px-8 pt-5">
          <div className="flex items-center gap-2 text-xs text-emerald-600">
            <span className="h-2 w-2 rounded-full bg-emerald-500" />
            Sistema actualizado
          </div>
          <div className="flex items-center gap-3 rounded-full bg-white px-2 py-1.5 shadow-sm">
            <div className="grid h-8 w-8 place-items-center rounded-full bg-[#0A1128] text-xs font-bold text-white">
              {iniciales}
            </div>
            <div className="pr-2">
              <p className="text-sm font-semibold leading-4">{usuario?.nombre}</p>
              <p className="text-[11px] capitalize text-slate-400">{usuario?.rol?.toLowerCase()}</p>
            </div>
          </div>
        </header>
        <main className="px-8 pb-10 pt-4">
          <div className="mb-6 flex items-start justify-between">
            <div>
              <p className="text-[11px] font-semibold tracking-[0.16em] text-brand">{kicker}</p>
              <h1 className="mt-1 text-3xl font-bold tracking-tight text-slate-900">{titulo}</h1>
              <p className="mt-1 text-sm text-slate-500">{subtitulo}</p>
            </div>
            <p className="pt-2 text-sm capitalize text-slate-400">{fechaLarga()}</p>
          </div>
          {children}
        </main>
      </div>
    </div>
  )
}
