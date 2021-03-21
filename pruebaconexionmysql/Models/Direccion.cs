using System;
using System.Collections.Generic;

#nullable disable

namespace pruebaconexionmysql.Models
{
    public partial class Direccion
    {
        public int Calle { get; set; }
        public string Localidad { get; set; }
        public string Provincia { get; set; }
        public string Numero { get; set; }
        public string CodigoPostal { get; set; }
        public int CentroPracticasIdCentro { get; set; }
    }
}
