-- Script used for local environment, create production environment with secret passwords instead
-- Set passwords as environment variables.
CREATE USER shop_creator WITH PASSWORD 'dbcreator';
CREATE USER shop_account_manager WITH PASSWORD 'dbaccountmanager';

REVOKE ALL ON DATABASE furniture_shop FROM PUBLIC;
REVOKE ALL ON DATABASE postgres FROM PUBLIC;
GRANT CONNECT ON DATABASE furniture_shop TO shop_creator, shop_account_manager;

REVOKE ALL ON SCHEMA public FROM public;
GRANT USAGE ON SCHEMA public TO shop_creator, shop_account_manager;
GRANT CREATE ON SCHEMA public TO shop_creator;