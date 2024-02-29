import React, { useState } from "react";

const Index = ({
  title = "Title",
  subtitle = "Subtitle",
  description = "Description",
  image = "Image",
  target = "/",
}) => {
  const [isHovered, setIsHovered] = useState(false);

  const handleMouseOver = () => setIsHovered(true);
  const handleMouseOut = () => setIsHovered(false);

  return (
    <a
      href={target}
      onMouseOver={handleMouseOver}
      onMouseOut={handleMouseOut}
      className="text-black no-underline"
    >
      <div className="flex flex-col h-64 w-full max-w-64 bg-gray-200 text-black rounded-xl overflow-hidden transition duration-200 ease-in-out transform hover:scale-105">
        <div className={isHovered ? "h-0 hidden" : "h-2/4 flex"}>
          <img
            src={image ? image : "img/placeholder.jpg"}
            className={`object-cover w-full h-auto ${
              !image ? "filter grayscale blur-md" : ""
            }`}
            alt="Card Image"
          />
        </div>
        <div className="flex flex-col gap-1 p-4">
          <h3 className="font-semibold text-xl">{title}</h3>
          <span className="italic">{subtitle}</span>
          <p
            className={
              isHovered
                ? "text-ellipsis overflow-clip h-full line-clamp-6 break-words"
                : "hidden"
            }
          >
            {description}
          </p>
        </div>
      </div>
    </a>
  );
};

export default Index;
