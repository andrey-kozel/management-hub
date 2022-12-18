const LoginPage = () => {
  return (
    <a href={`${process.env.REACT_APP_API_GATEWAY_URL}/oauth2/authorization/github`}>Authorize with github</a>
  );
};

export default LoginPage;
