part of 'login_bloc.dart';

abstract class LoginEvent extends Equatable {
  const LoginEvent();

  @override
  List<Object> get props => [];
}

class LoginUsernameChanged extends LoginEvent {
  const LoginUsernameChanged(this.username);

  final String username;

  @override
  List<Object> get props => [username];
}

class LoginPasswordChanged extends LoginEvent {
  const LoginPasswordChanged(this.password);

  final String password;

  @override
  List<Object> get props => [password];
}

class LoginPasswordSaveChanged extends LoginEvent {
  const LoginPasswordSaveChanged(this.passwordSave);

  final bool passwordSave;

  @override
  List<Object> get props => [passwordSave];
}

class LoginTokenChanged extends LoginEvent {
  const LoginTokenChanged(this.tokenInfo);

  final Map<String, dynamic> tokenInfo;

  @override
  List<Object> get props => [tokenInfo];
}

class LoginSubmitted extends LoginEvent {
  const LoginSubmitted();
}
