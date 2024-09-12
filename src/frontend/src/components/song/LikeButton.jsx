import { FaHeart } from "react-icons/fa";
import { FaRegHeart } from "react-icons/fa";

const LikeButton = () => {
    return (
        <div className="flex items-center justify-center p-1.5 text-center rounded-full cursor-pointer">
            <FaHeart color="#c00" />
        </div>
    );
};

export default LikeButton;
