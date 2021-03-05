import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { NuevoUsuario } from '../Modelo/nuevo-usuario';
import { AuthService } from '../Service/auth.service';
import { TokenService } from '../Service/token.service';

@Component({
  selector: 'app-resgistro',
  templateUrl: './resgistro.component.html',
  styleUrls: ['./resgistro.component.css']
})
export class ResgistroComponent implements OnInit {

  //variables 

  isRegister  =false;
  isRegisterFail = false;
  nuevoUsuario: NuevoUsuario;
  nombre: string;
  nombreUsuario: string;
  email: string;
  password: string;
  errMsj: string;
  isLogged = false;


  constructor(private tokenService: TokenService, private authService: AuthService, private router: Router, private toastr:ToastrService) { }

  ngOnInit(): void {
    if(this.tokenService.getToken()){
      this.isLogged = true;
    }
  }

  onRegister():void{

    //creamos la cuenta

    this.nuevoUsuario = new NuevoUsuario(this.nombre, this.nombreUsuario, this.email, this.password);
    this.authService.nuevo(this.nuevoUsuario).subscribe(
      data =>{
        this.toastr.success('Cuenta creada', 'OK',{
          timeOut:3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['login']);
      },
      err => {
        this.errMsj = err.error.message;
        this.toastr.error(this.errMsj, 'Fallo',{
          timeOut:3000, positionClass: 'toast-top-center'
        });
        
      }
    );
  }

}
