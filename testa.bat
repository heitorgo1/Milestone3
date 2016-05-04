
    echo "Iniciando a Compilação dos testes......"

   java -cp "junit-4.10.jar;classes"  org.junit.runner.JUnitCore Model.GerenciadorBaralhoTest
   java -cp "junit-4.10.jar;classes"  org.junit.runner.JUnitCore Model.FileiraTest
   java -cp "junit-4.10.jar;classes"  org.junit.runner.JUnitCore Model.FundacaoTest
   java -cp "junit-4.10.jar;classes"  org.junit.runner.JUnitCore Model.GerenciadorDescarteTest
   java -cp "junit-4.10.jar;classes"  org.junit.runner.JUnitCore Model.GerenciadorFileirasTest
   java -cp "junit-4.10.jar;classes"  org.junit.runner.JUnitCore Model.GerenciadorFundacoesTest
   java -cp "junit-4.10.jar;classes"  org.junit.runner.JUnitCore Model.RegraInsercaoPacienciaTest
   
   java -cp "junit-4.10.jar;classes"  org.junit.runner.JUnitCore freecelltest.GerenciadorCelulaLivreTest
   java -cp "junit-4.10.jar;classes"  org.junit.runner.JUnitCore freecelltest.RegraInsercaoFreecellTest
   java -cp "junit-4.10.jar;classes"  org.junit.runner.JUnitCore freecelltest.VerificaCelulaLivreTest
   