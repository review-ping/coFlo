import SignupForm from '@components/Signup/SignupForm.tsx';

const SignupContent = () => {
  return (
    <div className="w-full h-full flex items-center justify-center">
      <div className="absolute inset-0 pointer-events-none">
        <img
          src="/images/signup_bg_image.webp"
          alt="sign up"
          className="w-full h-full object-cover"
        />
      </div>
      <div className="relative inset-x-10 mx-auto z-10 w-full max-w-md px-4">
        <SignupForm />
      </div>
    </div>
  );
};

export default SignupContent;
