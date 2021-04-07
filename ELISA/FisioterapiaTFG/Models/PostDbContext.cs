using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata;

#nullable disable

namespace FisioterapiaTFG.Models
{
    public partial class PostDbContext : DbContext
    {
        public PostDbContext()
        {
        }

        public PostDbContext(DbContextOptions<PostDbContext> options)
            : base(options)
        {
        }

        public virtual DbSet<Alumno> Alumnos { get; set; }
        public virtual DbSet<AlumnoErasmu> AlumnoErasmus { get; set; }
        public virtual DbSet<Asignatura> Asignaturas { get; set; }
        public virtual DbSet<Centropractica> Centropracticas { get; set; }
        public virtual DbSet<Coordinadorpractica> Coordinadorpracticas { get; set; }
        public virtual DbSet<Direccion> Direccions { get; set; }
        public virtual DbSet<Peticion> Peticions { get; set; }
        public virtual DbSet<Rotatorio> Rotatorios { get; set; }
        public virtual DbSet<Tutorpractica> Tutorpracticas { get; set; }
        public virtual DbSet<Unidad> Unidads { get; set; }
        public virtual DbSet<Usuario> Usuarios { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (!optionsBuilder.IsConfigured)
            {
#warning To protect potentially sensitive information in your connection string, you should move it out of source code. You can avoid scaffolding the connection string by using the Name= syntax to read it from configuration - see https://go.microsoft.com/fwlink/?linkid=2131148. For more guidance on storing connection strings, see http://go.microsoft.com/fwlink/?LinkId=723263.
                optionsBuilder.UseMySql("server=localhost;database=mydb;uid=root;pwd=root", Microsoft.EntityFrameworkCore.ServerVersion.FromString("8.0.22-mysql"));
            }
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Alumno>(entity =>
            {
                entity.HasKey(e => e.UsuarioDni)
                    .HasName("PRIMARY");

                entity.ToTable("alumno");

                entity.Property(e => e.UsuarioDni)
                    .HasColumnType("varchar(45)")
                    .HasColumnName("Usuario_DNI")
                    .HasCharSet("utf8")
                    .HasCollation("utf8_general_ci");

                entity.Property(e => e.Apellidos)
                    .HasColumnType("varchar(45)")
                    .HasCharSet("utf8")
                    .HasCollation("utf8_general_ci");

                entity.Property(e => e.AsignaturaIdAsignatura).HasColumnName("Asignatura_IdAsignatura");

                entity.Property(e => e.CentroUniversitario)
                    .HasColumnType("varchar(45)")
                    .HasCharSet("utf8")
                    .HasCollation("utf8_general_ci");

                entity.Property(e => e.Nombre)
                    .HasColumnType("varchar(45)")
                    .HasCharSet("utf8")
                    .HasCollation("utf8_general_ci");

                entity.Property(e => e.RotatorioNombre)
                    .IsRequired()
                    .HasColumnType("varchar(45)")
                    .HasColumnName("Rotatorio_Nombre")
                    .HasCharSet("utf8")
                    .HasCollation("utf8_general_ci");
            });

            modelBuilder.Entity<AlumnoErasmu>(entity =>
            {
                entity.HasKey(e => e.AlumnoUsuarioDni)
                    .HasName("PRIMARY");

                entity.ToTable("alumno_erasmus");

                entity.Property(e => e.AlumnoUsuarioDni)
                    .HasColumnType("varchar(45)")
                    .HasColumnName("Alumno_Usuario_DNI")
                    .HasCharSet("utf8")
                    .HasCollation("utf8_general_ci");

                entity.Property(e => e.FechaLlegada).HasColumnType("date");

                entity.Property(e => e.FechaPartida).HasColumnType("date");
            });

            modelBuilder.Entity<Asignatura>(entity =>
            {
                entity.HasKey(e => e.IdAsignatura)
                    .HasName("PRIMARY");

                entity.ToTable("asignatura");

                entity.Property(e => e.IdAsignatura).ValueGeneratedNever();
            });

            modelBuilder.Entity<Centropractica>(entity =>
            {
                entity.HasKey(e => e.IdCentro)
                    .HasName("PRIMARY");

                entity.ToTable("centropracticas");

                entity.Property(e => e.IdCentro).ValueGeneratedNever();

                entity.Property(e => e.Nombre)
                    .HasColumnType("varchar(45)")
                    .HasCharSet("utf8")
                    .HasCollation("utf8_general_ci");

                entity.Property(e => e.Telefono)
                    .HasColumnType("varchar(12)")
                    .HasCharSet("utf8")
                    .HasCollation("utf8_general_ci");
            });

            modelBuilder.Entity<Coordinadorpractica>(entity =>
            {
                entity.HasKey(e => e.UsuarioDni)
                    .HasName("PRIMARY");

                entity.ToTable("coordinadorpracticas");

                entity.Property(e => e.UsuarioDni)
                    .HasColumnType("varchar(45)")
                    .HasColumnName("Usuario_DNI")
                    .HasCharSet("utf8")
                    .HasCollation("utf8_general_ci");
            });

            modelBuilder.Entity<Direccion>(entity =>
            {
                entity.HasKey(e => new { e.Calle, e.Localidad, e.Provincia, e.Numero })
                    .HasName("PRIMARY")
                    .HasAnnotation("MySql:IndexPrefixLength", new[] { 0, 0, 0, 0 });

                entity.ToTable("direccion");

                entity.Property(e => e.Localidad)
                    .HasColumnType("varchar(45)")
                    .HasCharSet("utf8")
                    .HasCollation("utf8_general_ci");

                entity.Property(e => e.Provincia)
                    .HasColumnType("varchar(45)")
                    .HasCharSet("utf8")
                    .HasCollation("utf8_general_ci");

                entity.Property(e => e.Numero)
                    .HasColumnType("varchar(45)")
                    .HasCharSet("utf8")
                    .HasCollation("utf8_general_ci");

                entity.Property(e => e.CentroPracticasIdCentro).HasColumnName("CentroPracticas_IdCentro");

                entity.Property(e => e.CodigoPostal)
                    .HasColumnType("varchar(45)")
                    .HasCharSet("utf8")
                    .HasCollation("utf8_general_ci");
            });

            modelBuilder.Entity<Peticion>(entity =>
            {
                entity.HasKey(e => e.IdPeticion)
                    .HasName("PRIMARY");

                entity.ToTable("peticion");

                entity.Property(e => e.IdPeticion)
                    .ValueGeneratedNever()
                    .HasColumnName("idPeticion");

                entity.Property(e => e.AlumnoUsuarioDni)
                    .IsRequired()
                    .HasColumnType("varchar(45)")
                    .HasColumnName("Alumno_Usuario_DNI")
                    .HasCharSet("utf8")
                    .HasCollation("utf8_general_ci");

                entity.Property(e => e.CoordinadorPracticasUsuarioDni)
                    .IsRequired()
                    .HasColumnType("varchar(45)")
                    .HasColumnName("CoordinadorPracticas_Usuario_DNI")
                    .HasCharSet("utf8")
                    .HasCollation("utf8_general_ci");

                entity.Property(e => e.FechaFin).HasColumnType("date");

                entity.Property(e => e.FechaIniciol).HasColumnType("date");

                entity.Property(e => e.Motivo)
                    .HasColumnType("varchar(100)")
                    .HasCharSet("utf8")
                    .HasCollation("utf8_general_ci");

                entity.Property(e => e.RotatorioNombre)
                    .IsRequired()
                    .HasColumnType("varchar(45)")
                    .HasColumnName("Rotatorio_Nombre")
                    .HasCharSet("utf8")
                    .HasCollation("utf8_general_ci");
            });

            modelBuilder.Entity<Rotatorio>(entity =>
            {
                entity.HasKey(e => e.Nombre)
                    .HasName("PRIMARY");

                entity.ToTable("rotatorio");

                entity.Property(e => e.Nombre)
                    .HasColumnType("varchar(45)")
                    .HasCharSet("utf8")
                    .HasCollation("utf8_general_ci");

                entity.Property(e => e.FechaFin).HasColumnType("date");

                entity.Property(e => e.FechaInicio).HasColumnType("date");

                entity.Property(e => e.Turno)
                    .HasColumnType("varchar(10)")
                    .HasCharSet("utf8")
                    .HasCollation("utf8_general_ci");

                entity.Property(e => e.UnidadNombre)
                    .IsRequired()
                    .HasColumnType("varchar(20)")
                    .HasColumnName("Unidad_Nombre")
                    .HasCharSet("utf8")
                    .HasCollation("utf8_general_ci");
            });

            modelBuilder.Entity<Tutorpractica>(entity =>
            {
                entity.HasKey(e => e.UsuarioDni)
                    .HasName("PRIMARY");

                entity.ToTable("tutorpracticas");

                entity.Property(e => e.UsuarioDni)
                    .HasColumnType("varchar(45)")
                    .HasColumnName("Usuario_DNI")
                    .HasCharSet("utf8")
                    .HasCollation("utf8_general_ci");

                entity.Property(e => e.UnidadNombre)
                    .IsRequired()
                    .HasColumnType("varchar(20)")
                    .HasColumnName("Unidad_Nombre")
                    .HasCharSet("utf8")
                    .HasCollation("utf8_general_ci");
            });

            modelBuilder.Entity<Unidad>(entity =>
            {
                entity.HasKey(e => e.Nombre)
                    .HasName("PRIMARY");

                entity.ToTable("unidad");

                entity.Property(e => e.Nombre)
                    .HasColumnType("varchar(20)")
                    .HasCharSet("utf8")
                    .HasCollation("utf8_general_ci");

                entity.Property(e => e.CentroPracticasIdCentro).HasColumnName("CentroPracticas_IdCentro");

                entity.Property(e => e.Planta)
                    .HasColumnType("varchar(45)")
                    .HasCharSet("utf8")
                    .HasCollation("utf8_general_ci");
            });

            modelBuilder.Entity<Usuario>(entity =>
            {
                entity.HasKey(e => e.Dni)
                    .HasName("PRIMARY");

                entity.ToTable("usuario");

                entity.Property(e => e.Dni)
                    .HasColumnType("varchar(45)")
                    .HasColumnName("DNI")
                    .HasCharSet("utf8")
                    .HasCollation("utf8_general_ci");

                entity.Property(e => e.ContraseÃA)
                    .IsRequired()
                    .HasColumnType("varchar(45)")
                    .HasColumnName("ContraseÃ±a")
                    .HasCharSet("utf8")
                    .HasCollation("utf8_general_ci");

                entity.Property(e => e.CorreoElectronico)
                    .IsRequired()
                    .HasColumnType("varchar(50)")
                    .HasCharSet("utf8")
                    .HasCollation("utf8_general_ci");
            });

            OnModelCreatingPartial(modelBuilder);
        }

        partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
    }
}
