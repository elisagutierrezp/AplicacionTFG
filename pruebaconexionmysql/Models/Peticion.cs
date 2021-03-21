using System;
using System.Collections.Generic;

#nullable disable

namespace pruebaconexionmysql.Models
{
    public partial class Peticion
    {
        public int IdPeticion { get; set; }
        public int? Aceptada { get; set; }
        public string Motivo { get; set; }
        public DateTime? FechaIniciol { get; set; }
        public DateTime? FechaFin { get; set; }
        public string RotatorioNombre { get; set; }
        public string AlumnoUsuarioDni { get; set; }
        public string CoordinadorPracticasUsuarioDni { get; set; }
    }
}
