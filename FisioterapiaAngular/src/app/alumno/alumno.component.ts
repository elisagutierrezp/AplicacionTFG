import { Component, OnInit } from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar';



@Component({
  selector: 'app-alumno',
  templateUrl: './alumno.component.html',
  styleUrls: ['./alumno.component.css']
})
export class AlumnoComponent implements OnInit {

  tieneRotatorio : boolean;
  
  constructor(private _snackBar: MatSnackBar) { 

    this.tieneRotatorio=false;
  }

  ngOnInit(): void {
  }

  openSnackBar() {
    this._snackBar.open("No dispones de ningún rotatorio actualmente", "OK", {
      duration: 2000,
    });

}
}
