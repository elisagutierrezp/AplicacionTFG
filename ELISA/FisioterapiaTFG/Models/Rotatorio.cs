using System;
using System.Collections.Generic;

#nullable disable

namespace FisioterapiaTFG.Models
{
    public partial class Rotatorio
    {
        public DateTime? FechaInicio { get; set; }
        public DateTime? FechaFin { get; set; }
        public string Turno { get; set; }
        public int? HorasTotales { get; set; }
        public string Nombre { get; set; }
        public string UnidadNombre { get; set; }
    }
}
