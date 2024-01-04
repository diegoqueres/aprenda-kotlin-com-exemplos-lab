enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

data class Usuario(val id: Int, val nome: String)

data class ConteudoEducacional(val id: Int, var nome: String, val duracao: Int = 5)

data class Nota(val id: Int, val usuario: Usuario, val conteudo: ConteudoEducacional, val valor: Float)

data class Formacao(val id: Int, var nome: String, val nivel: Nivel, val conteudos: MutableSet<ConteudoEducacional>) {
    val inscritos = mutableSetOf<Usuario>()
    
    fun adicionarConteudo(conteudo: ConteudoEducacional) {
        conteudos.add(conteudo)
    }
    
    fun matricular(usuario: Usuario) {
        inscritos.add(usuario)
    }
    
    fun imprimirConteudosEducacionais() {
        println("Os conteúdos para a formação $nome são: ${conteudos.map(ConteudoEducacional::nome).sorted()}")
    }
    
    fun imprimirInscritos() {
        println("Os inscritos para a formação $nome são: ${inscritos.map(Usuario::nome).sorted()}. Há ${inscritos.size} inscritos.")
    }
}

fun main() {  
    // Cria usuários
    val usuario1 = Usuario(1, "João")
    val usuario2 = Usuario(2, "Maria")
    val usuario3 = Usuario(3, "Zeca")
    
    // Cria conteúdos
    val conteudo1 = ConteudoEducacional(1, "Introdução a Kotlin")
    val conteudo2 = ConteudoEducacional(2, "Kotlin Básico I", 10)
    val conteudo3 = ConteudoEducacional(3, "Kotlin Básico II", 15)
    
    // Cria formação
    val formacaoKotlin = Formacao(id = 1, nome = "Kotlin", nivel = Nivel.BASICO, conteudos = mutableSetOf(conteudo1, conteudo2, conteudo3))
    
    // Testa adicionar conteúdos repetidos
    formacaoKotlin.adicionarConteudo(ConteudoEducacional(4, "Kotlin Básico III", 20))
    formacaoKotlin.adicionarConteudo(ConteudoEducacional(4, "Kotlin Básico III", 20))
    formacaoKotlin.imprimirConteudosEducacionais()
    
    // Testa matricular usuários (alunos)
    formacaoKotlin.matricular(usuario1)
    formacaoKotlin.matricular(usuario2)
    formacaoKotlin.matricular(usuario3)
    formacaoKotlin.imprimirInscritos()
    
    // Testa processar notas
    println()
    println("Processando notas...")
    val notas: List<Nota> = listOf(
    	Nota(1, usuario1, conteudo1, 7.5f),
        Nota(2, usuario1, conteudo2, 8f),
    )
    val processarNota: (Nota) -> String = { nota -> "A nota de ${nota.usuario.nome} no conteúdo ${nota.conteudo.nome} é de ${nota.valor}" }  
    for (nota in notas) {
        println(processarNota(nota))
    }
}