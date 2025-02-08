import React from "react";

const Input = React.forwardRef(({ className, ...props }, ref) => {
  return (
    <input
      ref={ref}
      className={`border rounded-md p-2 ${className}`}
      {...props}
    />
  );
});

Input.displayName = "Input";

export { Input };
