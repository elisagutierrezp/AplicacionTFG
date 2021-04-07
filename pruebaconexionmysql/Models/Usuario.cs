using System;
using System.Collections.Generic;

#nullable disable

namespace pruebaconexionmysql.Models
{
    public partial class Usuario
    {
        public string CorreoElectronico { get; set; }
        public string Contraseña { get; set; }
        public string Dni { get; set; }
    }
}
