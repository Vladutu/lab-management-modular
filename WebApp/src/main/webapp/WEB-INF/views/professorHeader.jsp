<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Laboratory Management</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="/professor/home">Home</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="navbar-brand">Welcome ${welcomeUserDto.firstName}&nbsp;${welcomeUserDto.lastName}</li>
                <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="">Language<span
                        class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="?mylocale=en">English</a></li>
                        <li><a href="?mylocale=ro">Romanian</a></li>
                    </ul>
                </li>
                <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
            </ul>
        </div>
    </div>
</nav>
  