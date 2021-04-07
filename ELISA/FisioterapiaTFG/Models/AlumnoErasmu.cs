using System;
using System.Collections.Generic;

#nullable disable

namespace FisioterapiaTFG.Models
{
    public partial class AlumnoErasmu
    {
        public DateTime? FechaPartida { get; set; }
        public DateTime? FechaLlegada { get; set; }
        public int? HorasTotales { get; set; }
        public sbyte? RenovacionEstancia { get; set; }
        public string AlumnoUsuarioDni { get; set; }
    }
}
