import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {AngularMaterialModule} from './angular-material.module';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AlumnoComponent } from './alumno/alumno.component';
import { UserComponent } from './user/user.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AlumnoComponent,
    UserComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AngularMaterialModule,
    FlexLayoutModule,
    FormsModule, ReactiveFormsModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
