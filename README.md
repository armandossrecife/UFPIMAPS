HOW TO SETUP ENVIRONMENT - ANDROID STUDIO COM GITHUB
==
 
* 1) Criar uma conta no GitHub (https://github.com);
* 2) Baixar git para o seu sistema operacional (http://git-scm.com/downloads).
* 3) Baixar a aplicação Android Studio (https://developer.android.com/sdk/index.html);
* 4) Baixar a aplicação Android Studio, OBS: Lembre-se de instalar em uma pasta que não contenha espaços ou acentos, porque isso pode causar problemas no adroid studio.
* 5) Abrir o Android Studio;
* 6) Escolher a opção Configure à Settings;
* 7) Expandir a opção “Version Control” que aparece ao lado esquerdo.
* 8) Selecionar Git e atualizar o path do executável para o destino <Raiz do Git>/bin/git.exe. Clique na opção “test” para testar se o diretório foi atualizado corretamente, se sim clique em OK.
* 9) Na janela "Quick Start" escolher a opção -> Check out project from Version Control -> Checkout from -> GitHub;
* 10) Na janela "Login to GitHub", realizar login com a conta previamente criada;
* 11) Na janela “Set Master Code” inserir a senha "ufpimaps";
* 12) Na janela "Clone Repository" -> "Vcs Repository URL" inserir o link: (https://github.com/hugos94/UFPIMAPS);
* 13) O repositório deve ser posteriormente transferido para alguma conta do GitHub administrada pela UFPI;
* 14) Em "Parent Directory" escolher o local onde a aplicação será clonada;
* 15) Em "Directory Name" inserir "UFPIMAPS";
* 16) Na janela "Checkout From Version Control (Would you like to create an Android Studio project for the sources you have checked out to /../../../AndroidStudioProjects/UFPIMAPS2?)" aceitar a criação;
* 17) (Esta opção pode aparecer dependo da configuração previamente realizada pelo Android Studio) Na janela "Import Project" escolher a opção "Create project from existings sources" -> Gradle" -> "Next" -> "Next" -> "Finished";
* 18) Acessar o link (https://code.google.com/apis/console/?noredirect&pli=1#) e criar o projeto;
* 19) Ainda no link, na opção Services, ativar o serviço de Google Maps API v2;
* 20) Gerar a API Key para ser adicionada res/values/strings.xml, que será reponsável por conectar a aplicação aos serviços do Android;
* 21) Clique na opção API Accesse crie um projeto;
* 22) Abrir o terminal e digitar o seguinte comando para obter o SHA1, trocando o c:\users\your_user_name\.android\debug.keystore pelo caminho do seu computador: ( keytool -list -v -keystore c:\users\your_user_name\.android\debug.keystore -alias androiddebugkey -storepass android -keypass android ) ;
* 23) Copie o SHA1 gerado;
* 24) Clique em Create New Android Key... e coloque o SHA1 Fingerprint gerado e o nome do projeto, separados por ponto-e-virgula, sem espaço (como em: AIzaSyDXLEO_RXbR8x4NUAjWb7hGNCnMQ5KCWro;com.ufpimaps).
* 25) Clique em Close e a API KEY estará na página que aparecerá.
