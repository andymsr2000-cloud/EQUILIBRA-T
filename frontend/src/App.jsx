import { AppProvider, useApp } from './context/AppContext'
import LoginView from './views/LoginView'
import DashboardView from './views/DashboardView'
import AntropometriaView from './views/AntropometriaView'
import PausasActivasView from './views/PausasActivasView'
import ReportesView from './views/ReportesView'

function Shell() {
  const { usuario, vista } = useApp()
  if (!usuario) return <LoginView />
  if (vista === 'antropometria') return <AntropometriaView />
  if (vista === 'pausas') return <PausasActivasView />
  if (vista === 'reportes') return <ReportesView />
  return <DashboardView />
}

export default function App() {
  return (
    <AppProvider>
      <Shell />
    </AppProvider>
  )
}
