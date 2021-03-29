using Microsoft.AspNetCore.Identity;
using System;
using System.Collections.Generic;

#nullable disable

namespace pruebaconexionmysql.Models
{
    public partial class Usuario: IdentityUser //para permitir hacer el login
    {
        public string CorreoElectronico { get; set; }
        public string Contraseña { get; set; }
        public string Dni { get; set; }
    }
}
