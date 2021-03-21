using System;
using System.Collections.Generic;

#nullable disable

namespace pruebaconexionmysql.Models
{
    public partial class Unidad
    {
        public string Nombre { get; set; }
        public string Planta { get; set; }
        public int CentroPracticasIdCentro { get; set; }
    }
}
