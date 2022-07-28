import 'package:easy_localization/easy_localization.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:abcleaver/app/simple_app_bar.dart';
import 'package:abcleaver/commons/theme.dart';
import 'package:abcleaver/features/settings/setting.dart';

class SettingPage extends StatelessWidget {
  const SettingPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final scaffoldKey = GlobalKey<ScaffoldState>();

    return Scaffold(
        backgroundColor: AppTheme.of(context).primaryBackground,
        key: scaffoldKey,
        appBar: SimpleAppBar('settings.value'.tr()),
        body: BlocProvider(
            create: (context) => SettingBloc(), child: const SettingForm()));
  }
}
