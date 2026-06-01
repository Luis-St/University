import type { ReactNode } from "react";

interface ModalProps {
	isOpen: boolean;
	onClose: () => void;
	children: ReactNode;
}

function Modal({ isOpen, onClose, children }: ModalProps) {
	if (!isOpen) return null;
	
	return (
		<div onClick={onClose}>
			<div onClick={(e) => e.stopPropagation()}>
				<button onClick={onClose}>
					&times;
				</button>
				{children}
			</div>
		</div>
	);
}

export default Modal;
