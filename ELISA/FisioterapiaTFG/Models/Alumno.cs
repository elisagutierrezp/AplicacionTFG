using System;
using System.Collections.Generic;

#nullable disable

namespace FisioterapiaTFG.Models
{
    public partial class Alumno
    {
        public string Nombre { get; set; }
        public string Apellidos { get; set; }
        public int? CursoAcademico { get; set; }
        public string CentroUniversitario { get; set; }
        public string UsuarioDni { get; set; }
        public int AsignaturaIdAsignatura { get; set; }
        public string RotatorioNombre { get; set; }
    }
}
