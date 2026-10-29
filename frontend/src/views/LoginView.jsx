import { useState } from 'react'
import { Ico, LogoEscudo } from '../components/Iconos'
import { useApp } from '../context/AppContext'
import { ROLES } from '../data/seed'

export default function LoginView() {
  const { login, errorAuth } = useApp()
  const [rol, setRol] = useState('PERITO')
  const [cip, setCip] = useState('')
  const [password, setPassword] = useState('')

  const enviar = (e) => {
    e.preventDefault()
    login({ cip, password, rol })
  }

  return (
    <div className="login-bg flex min-h-screen items-center justify-center px-4 py-10">
      <form
        onSubmit={enviar}
        className="w-full max-w-[440px] rounded-[28px] bg-white p-8 shadow-2xl shadow-black/30"
      >
        <div className="flex items-center gap-3">
          <LogoEscudo className="h-12 w-12" />
          <div>
            <p className="text-lg font-extrabold tracking-wide text-slate-900">EQUILIBRA-T</p>
            <p className="text-xs text-slate-400">Criminalística PNP · Tacna</p>
          </div>
        </div>

        <p className="mt-6 flex items-center gap-2 text-[11px] font-semibold tracking-[0.14em] text-brand">
          <Ico.shield className="h-3.5 w-3.5" />
          ACCESO INSTITUCIONAL SEGURO
        </p>
        <h1 className="mt-3 text-[32px] font-bold leading-[1.15] text-slate-900">
          Bienestar que protege a quienes investigan
        </h1>
        <p className="mt-2 text-sm text-slate-500">
          Ingresa con tus credenciales institucionales para acceder al sistema.
        </p>

        <p className="mt-6 text-sm font-semibold text-slate-800">Selecciona tu rol</p>
        <div className="mt-2 grid grid-cols-3 gap-2">
          {ROLES.map((item) => {
            const Icon = Ico[item.icon]
            const activo = rol === item.id
            return (
              <button
                key={item.id}
                type="button"
                onClick={() => setRol(item.id)}
                className={`flex items-center justify-center gap-1.5 rounded-xl border px-2 py-2.5 text-sm ${
                  activo
                    ? 'border-blue-200 bg-blue-50 font-semibold text-brand'
                    : 'border-slate-200 text-slate-500'
                }`}
              >
                <Icon className="h-4 w-4" />
                {item.label}
              </button>
            )
          })}
        </div>

        <label className="mt-5 block text-sm font-semibold text-slate-800">
          Código de Identificación Policial (CIP)
        </label>
        <div className="mt-2 flex items-center gap-2 rounded-xl border border-slate-200 px-3 py-2.5">
          <Ico.user className="h-4 w-4 text-slate-400" />
          <input
            value={cip}
            onChange={(e) => setCip(e.target.value.replace(/\D/g, '').slice(0, 8))}
            placeholder="Ingresa 8 caracteres"
            className="w-full bg-transparent text-sm outline-none"
            inputMode="numeric"
            maxLength={8}
            required
          />
        </div>

        <label className="mt-4 block text-sm font-semibold text-slate-800">Contraseña</label>
        <div className="mt-2 flex items-center gap-2 rounded-xl border border-slate-200 px-3 py-2.5">
          <Ico.shield className="h-4 w-4 text-slate-400" />
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="Ingresa tu contraseña"
            className="w-full bg-transparent text-sm outline-none"
            required
          />
        </div>

        {errorAuth ? <p className="mt-3 text-sm text-rose-600">{errorAuth}</p> : null}

        <button
          type="submit"
          className="mt-6 flex w-full items-center justify-center gap-2 rounded-xl bg-brand py-3 text-sm font-semibold text-white hover:bg-brand-dark"
        >
          Ingresar al sistema
          <span>›</span>
        </button>
        <p className="mt-4 text-center text-xs text-slate-400">
          ¿Problemas de acceso? Contacta a Soporte TI · Anexo 204
        </p>
        <p className="mt-2 text-center text-[11px] text-slate-400">
          Demo: CIP 10000001 (Jefatura) o 20000001 (Perito) · clave EquilibraT2025
        </p>
      </form>
    </div>
  )
}
