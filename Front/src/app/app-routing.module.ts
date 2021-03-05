import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login.component';
import { ResgistroComponent } from './auth/resgistro.component';
import { AddComponent } from './Imagenes/add/add.component';
import { EditComponent } from './Imagenes/edit/edit.component';
import { ListaComponent } from './Imagenes/lista/lista.component';
import { IndexComponent } from './index/index.component';
import { ImgGuardService as guard } from './guards/img-guard.service'
import { CategoriaGatoComponent } from './Imagenes/categoria-gato/categoria-gato.component';

const routes: Routes = [
  {
    path: 'lista', component: ListaComponent 
  },
  {
    path: 'add', component: AddComponent, canActivate: [guard], data: { expectedRol: ['admin'] }
  },
  {
    path: 'edit', component: EditComponent, canActivate: [guard], data: { expectedRol: ['admin'] }
  },
  {
    path: '', component: IndexComponent
  },
  {
    path: 'login', component: LoginComponent
  },
  {
    path: 'registro', component: ResgistroComponent
  },
  {
    path: 'gatos', component: CategoriaGatoComponent
    
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
