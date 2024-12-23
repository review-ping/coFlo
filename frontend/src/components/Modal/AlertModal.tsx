import { AlertModalProps } from 'types/modal.ts';
import { TriangleAlert } from 'lucide-react';

const AlertModal = ({
  content,
  onConfirm,
  className = '',
  icon: Icon = TriangleAlert,
  iconSize = 48,
  iconClassName = 'text-state-warning',
}: AlertModalProps) => {
  return (
    <div className="fixed inset-0 flex items-center justify-center z-50">
      <div className="fixed inset-0 bg-black opacity-50"></div>
      <div
        className={`flex flex-col items-center justify-center space-y-2 py-6 bg-white rounded-lg p-6 max-w-sm mx-4 relative z-10 border-2 border-primary-500 ${className}`}
      >
        <div className="w-[72px] h-[72px] flex items-center justify-center">
          <Icon className={iconClassName} size={iconSize} />
        </div>
        <div className="my-2">
          {content.map((text, index) => (
            <p key={index} className="text-center text-lg text-primary-500">
              {text}
            </p>
          ))}
        </div>
        <div className="flex justify-center">
          <button
            className="px-6 py-2 rounded-full border-2 border-primary-500 bg-primary-500 text-white"
            onClick={() => onConfirm()}
          >
            확인
          </button>
        </div>
      </div>
    </div>
  );
};

export default AlertModal;
